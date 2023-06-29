package com.example.rentacarproject.service.impl;

import com.example.rentacarproject.converter.ReservationConverter;
import com.example.rentacarproject.dto.ReservationRequest;
import com.example.rentacarproject.dto.ReservationResponse;
import com.example.rentacarproject.entity.Car;
import com.example.rentacarproject.entity.Reservation;
import com.example.rentacarproject.entity.User;
import com.example.rentacarproject.repository.CarRepository;
import com.example.rentacarproject.repository.ReservationRepository;
import com.example.rentacarproject.repository.UserRepository;
import com.example.rentacarproject.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationConverter reservationConverter;
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationConverter reservationConverter, CarRepository carRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationConverter = reservationConverter;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Reservation saveReservation(ReservationRequest request) {
        return reservationRepository.save(reservationConverter.toReservation(request));
    }

    @Override
    public ReservationResponse findReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Reservation with ID: %s not found", id)));
        return reservationConverter.toReservationResponse(reservation);
    }
    @Override
    public Set<Reservation> findReservationByUserEmail(String email) {
        User user = userRepository.findByEmail(email).
                orElseThrow(()-> new RuntimeException(String.format("User with email: %s not found", email)));

        return reservationRepository.findByUser(user).orElse(Collections.emptySet());
    }

    @Override
    public Set<Reservation> findReservationByCarId(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new RuntimeException("No Car with this id"));

        return reservationRepository.findByCar(car).orElse(Collections.emptySet());
    }

    @Override
    public ReservationResponse changeCar(Long id ,Long carId) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("There is no reservation with that ID"));
        Car updatedCar = carRepository.findById(carId).orElseThrow(
                ()-> new RuntimeException("There is no car with that ID"));
            reservation.setCar(updatedCar);
        return reservationConverter.toReservationResponse(reservation);
    }
        /** With the change of the dates
         * the totalPrice will change a swell */
    @Override
    public ReservationResponse changeDates(Long id, LocalDate dateIn, LocalDate dateOut) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("There is no reservation with that ID"));
        if (Objects.nonNull(dateIn)) {
            reservation.setDateIn(dateIn);
        }
        if (Objects.nonNull(dateOut)) {
            reservation.setDateOut(dateOut);
        }
        Duration diff = Duration.between(reservation.getDateIn().atStartOfDay(), reservation.getDateOut().atStartOfDay() );
        long diffDays = diff.toDays();
        long oldStays = reservation.getStays();
        reservation.setStays(diffDays);
        reservation.setTotalPrice((reservation.getTotalPrice() / oldStays) * diffDays);

        return reservationConverter.toReservationResponse(reservation);
    }

    @Override
    public Set<Reservation> findReservationByPeriodWithNative(LocalDate dateIn, LocalDate dateOut) {
        return reservationRepository.getReservationsByIntervalWithNative(
                        dateIn.toString(), dateOut.toString() )
                .orElse(Collections.emptySet());
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }



}
