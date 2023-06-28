package com.example.rentacarproject.converter;

import com.example.rentacarproject.dto.CarRequest;
import com.example.rentacarproject.dto.CarResponse;
import com.example.rentacarproject.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {

    public Car toCar (CarRequest request) {
        return Car.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .seats(request.getSeats())
                .pricePerDay(request.getPricePerDay())
                .build();
    }

    public CarResponse toResponse(Car savedCar){
        return new CarResponse(savedCar.getBrand(), savedCar.getModel(), savedCar.getPricePerDay());
    }
}
