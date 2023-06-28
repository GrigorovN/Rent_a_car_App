package com.example.rentacarproject.service;

import com.example.rentacarproject.dto.ReservationRequest;
import com.example.rentacarproject.dto.ReservationResponse;
import com.example.rentacarproject.entity.Reservation;

import java.time.LocalDate;
import java.util.Set;

public interface ReservationService {

    Reservation saveReservation(ReservationRequest request);

    ReservationResponse findReservationById(Long id);

    Set<Reservation> findReservationByPeriodWithNative(LocalDate dateIn, LocalDate dateOut);

}
