package com.example.rentacarproject.converter;

import com.example.rentacarproject.dto.ReservationRequest;
import com.example.rentacarproject.dto.ReservationResponse;
import com.example.rentacarproject.entity.Car;
import com.example.rentacarproject.entity.Reservation;
import com.example.rentacarproject.entity.User;
import com.example.rentacarproject.repository.CarRepository;
import com.example.rentacarproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class ReservationConverter {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CarRepository carRepository;

    public Reservation toReservation(ReservationRequest request) {
        Duration diff = Duration.between(request.getDateIn().atStartOfDay(), request.getDateOut().atStartOfDay() );
        long diffDays = diff.toDays();
        Reservation reservation = Reservation.builder()
                .user(getUser(request.getUserId()))
                .car(getCar(request.getCarId()))
                .dateIn(request.getDateIn())
                .dateOut(request.getDateOut())
                .stays(diffDays)
                .build();
        reservation.setTotalPrice(reservation.getStays() * getCar(request.getCarId()).getPricePerDay());
        return reservation;
    }

    public ReservationResponse toReservationResponse(Reservation reservation) {
        return ReservationResponse.builder()
                .user(reservation.getUser())
                .car(reservation.getCar())
                .stays(reservation.getStays())
                .dateIn(reservation.getDateIn())
                .dateOut(reservation.getDateOut())
                .id(reservation.getId())
                .totalPrice(reservation.getTotalPrice())
                .build();
    }

    User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "User with " + id + " not found" ));
    }

    Car getCar(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "User with " + id + " not found" ));
    }
}
