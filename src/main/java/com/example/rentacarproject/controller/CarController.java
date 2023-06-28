package com.example.rentacarproject.controller;

import com.example.rentacarproject.dto.CarRequest;
import com.example.rentacarproject.dto.CarResponse;
import com.example.rentacarproject.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="api/v2/car")
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping(path = "/register")
    public ResponseEntity<CarResponse> registerCar(@Valid @RequestBody CarRequest request) {
        CarResponse carResponse = carService.saveCar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(carResponse);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<CarResponse> getCar(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(carService.getCar(id));
    }

    @DeleteMapping(path = "/{id}")
    public HttpStatus deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
        return HttpStatus.ACCEPTED;
    }
    /** The only possible field that can be updated is the price
     * as per my understandings
     * @author Nikola Grigorov*/
    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> changePrice (@PathVariable Long id, @RequestParam Double price) {
        CarResponse carResponse = carService.updateCarPrice(id, price);
        return ResponseEntity.status(HttpStatus.OK).body(carResponse);
    }
}
