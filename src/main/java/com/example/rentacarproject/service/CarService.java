package com.example.rentacarproject.service;

import com.example.rentacarproject.dto.CarRequest;
import com.example.rentacarproject.dto.CarResponse;


public interface CarService {

    CarResponse saveCar(CarRequest carRequest);

    CarResponse getCar(Long id);

    void deleteCar(Long id);

    CarResponse updateCarPrice(Long id, Double price);
}
