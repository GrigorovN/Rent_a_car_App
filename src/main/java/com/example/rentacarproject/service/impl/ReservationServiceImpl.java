package com.example.rentacarproject.service.impl;

import com.example.rentacarproject.converter.ReservationConverter;
import com.example.rentacarproject.dto.ReservationRequest;
import com.example.rentacarproject.dto.ReservationResponse;
import com.example.rentacarproject.entity.Car;
import com.example.rentacarproject.entity.Reservation;
import com.example.rentacarproject.entity.User;
import com.example.rentacarproject.exception.NotFoundException;
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

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationConverter reservationConverter,
                                  CarRepository carRepository, UserRepository userRepository) {

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
                .orElseThrow(()-> new NotFoundException(String.format("Reservation with ID: %s not found", id)));

        return reservationConverter.toReservationResponse(reservation);
    }
    @Override
    public Set<Reservation> findReservationByUserEmail(String email) {

        User user = userRepository.findByEmail(email).
                orElseThrow(()-> new NotFoundException(String.format("User with email: %s not found", email)));

        return reservationRepository.findByUser(user).orElse(Collections.emptySet());
    }

    @Override
    public Set<Reservation> findReservationByCarId(Long id) {

        Car car = carRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("User with email: %d not found", id)));

        return reservationRepository.findByCar(car).orElse(Collections.emptySet());
    }

    @Override
    public ReservationResponse changeCar(Long id ,Long carId) {

        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("There is no reservation with that ID"));

        Car updatedCar = carRepository.findById(carId).orElseThrow(
                ()-> new RuntimeException("There is no car with that ID"));
            reservation.setCar(updatedCar);

        // Save the updated reservation
        Reservation updatedReservation = reservationRepository.save(reservation);

        return reservationConverter.toReservationResponse(updatedReservation);
    }

        /** With the change of the dates
         * the totalPrice will change as well */
    @Override
    public ReservationResponse changeDates(Long id, LocalDate dateIn, LocalDate dateOut) {

        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("There is no reservation with ID" + id));

        // Update the reservation dates if new dates are provided
        if (Objects.nonNull(dateIn)) {
            reservation.setDateIn(dateIn);
        }
        if (Objects.nonNull(dateOut)) {
            reservation.setDateOut(dateOut);
        }

        // Calculate the duration of the stay in days
        Duration diff = Duration.between(reservation.getDateIn().atStartOfDay(), reservation.getDateOut().atStartOfDay() );
        long diffDays = diff.toDays();

        // Update the stays and total price based on the new duration
        long oldStays = reservation.getStays();
        reservation.setStays(diffDays);
        reservation.setTotalPrice((reservation.getTotalPrice() / oldStays) * diffDays);

        // Save the updated reservation
        Reservation updatedReservation = reservationRepository.save(reservation);

        return reservationConverter.toReservationResponse(updatedReservation);
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
