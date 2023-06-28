package com.example.rentacarproject.service.impl;

import com.example.rentacarproject.converter.ReservationConverter;
import com.example.rentacarproject.dto.ReservationRequest;
import com.example.rentacarproject.dto.ReservationResponse;
import com.example.rentacarproject.entity.Reservation;
import com.example.rentacarproject.repository.ReservationRepository;
import com.example.rentacarproject.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationConverter reservationConverter;

    public ReservationServiceImpl(ReservationRepository reservationRepository, ReservationConverter reservationConverter) {
        this.reservationRepository = reservationRepository;
        this.reservationConverter = reservationConverter;
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
    public Set<Reservation> findReservationByPeriodWithNative(LocalDate dateIn, LocalDate dateOut) {
        return reservationRepository.getReservationsByIntervalWithNative(
                        dateIn.toString(), dateOut.toString() )
                .orElse(Collections.emptySet());

    }


}
