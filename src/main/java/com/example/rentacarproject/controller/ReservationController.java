package com.example.rentacarproject.controller;

import com.example.rentacarproject.converter.CarConverter;
import com.example.rentacarproject.converter.ReservationConverter;
import com.example.rentacarproject.dto.ReservationRequest;
import com.example.rentacarproject.dto.ReservationResponse;
import com.example.rentacarproject.entity.Reservation;
import com.example.rentacarproject.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private CarConverter carConverter;


    @GetMapping(path = "/{id}")
    public ResponseEntity<ReservationResponse> getById(@PathVariable("id") Long reservationId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(reservationService.findReservationById(reservationId));
    }
    /**
     * With this method you can find
     * did the car have a reservations
     * @author Nikola Grigorov */
    @GetMapping(path = "/car")
    public ResponseEntity<Set<ReservationResponse>> getByCarId(@RequestParam("id") Long id) {
        Set<ReservationResponse> reservationResponses = new HashSet<>();
        reservationService.findReservationByCarId(id).forEach(
                reservation -> {
                    ReservationResponse reservationResponse = reservationConverter.toReservationResponse(reservation);
                    reservationResponses.add(reservationResponse);
                }  );
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponses);
    }

    /**
     * With this method you can find all reservations of the user
     * All you need is his email
     * @author Nikola Grigorov */
    @GetMapping(path = "/email")
    public ResponseEntity<Set<ReservationResponse>> getByUserEmail(@RequestParam("email") String email) {
        Set<ReservationResponse> reservationResponses = new HashSet<>();
        reservationService.findReservationByUserEmail(email).forEach(
                reservation -> {
                    ReservationResponse reservationResponse = reservationConverter.toReservationResponse(reservation);
                    reservationResponses.add(reservationResponse);
                }  );
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponses);
    }

    @PostMapping()
    public ResponseEntity<Reservation> book(@RequestBody ReservationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.saveReservation(request));
    }
    @GetMapping(path = "/period-native")
    public ResponseEntity<Set<ReservationResponse>> getReservationByPeriod(@RequestParam("dateIn") LocalDate dateIn,
                                                                           @RequestParam("dateOut") LocalDate dateOut){
        Set<ReservationResponse> reservationResponses = new HashSet<>();
        reservationService.findReservationByPeriodWithNative(dateIn,dateOut).forEach(
                reservation -> {
                    ReservationResponse reservationResponse = reservationConverter.toReservationResponse(reservation);
                    reservationResponses.add(reservationResponse);
                }  );
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponses);
    }
    /** Method to change the current car of reservation
     * with other car from the Car Table
     * @param id  id of the reservation
     * @param carId  is the id of the new Car
     * @author Nikola Grigorov */
    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponse> updateCar(@PathVariable Long id,
                                                         @RequestParam("carId") Long carId){
        ReservationResponse reservationResponse = reservationService.changeCar(id, carId);
        return ResponseEntity.status(HttpStatus.OK).body(reservationResponse);
    }
    @PutMapping("/update-dates")
    public ResponseEntity<ReservationResponse> changeDates (@RequestParam("id") Long id,
                                                            @RequestParam("dateIn") LocalDate dateIn,
                                                            @RequestParam("dateOut") LocalDate dateOut){
        ReservationResponse reservationResponse =reservationService.changeDates(id, dateIn, dateOut);
        return ResponseEntity.status(HttpStatus.OK).body(reservationResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservationById (@PathVariable Long id){
        reservationService.deleteReservation(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Reservation was deleted");
    }
}
