package com.example.rentacarproject.controller;


import com.example.rentacarproject.converter.ReservationConverter;
import com.example.rentacarproject.dto.ReservationRequest;
import com.example.rentacarproject.dto.ReservationResponse;
import com.example.rentacarproject.entity.Reservation;
import com.example.rentacarproject.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/v2/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationConverter reservationConverter;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationResponse> getById(@PathVariable("id") Long reservationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.findReservationById(reservationId));
    }

    @PostMapping()
    public ResponseEntity<Reservation> book(@RequestBody ReservationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.saveReservation(request));
    }
    @GetMapping(path = "/period-native")
    public ResponseEntity<Set<ReservationResponse>> getReservationByPeriod(@RequestParam("dateIn") LocalDate start,
                                                                           @RequestParam("dateOut") LocalDate end){
        Set<ReservationResponse> reservationResponses = new HashSet<>();
        reservationService.findReservationByPeriodWithNative(start,end).forEach(
                reservation -> {
                    ReservationResponse reservationResponse = reservationConverter.toReservationResponse(reservation);
                    reservationResponses.add(reservationResponse);
                }  );
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponses);
    }
}
