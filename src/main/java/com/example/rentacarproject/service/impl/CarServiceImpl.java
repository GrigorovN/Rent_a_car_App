package com.example.rentacarproject.service.impl;

import com.example.rentacarproject.converter.CarConverter;
import com.example.rentacarproject.dto.CarRequest;
import com.example.rentacarproject.dto.CarResponse;
import com.example.rentacarproject.entity.Car;
import com.example.rentacarproject.repository.CarRepository;
import com.example.rentacarproject.service.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarConverter carConverter;

    public CarServiceImpl(CarRepository carRepository, CarConverter carConverter) {
        this.carRepository = carRepository;
        this.carConverter = carConverter;
    }


    @Override
    public CarResponse saveCar(CarRequest carRequest) {
       Car car = carConverter.toCar(carRequest);
       return carConverter.toResponse(carRepository.save(car));
    }

    @Override
    public CarResponse getCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow( () -> new RuntimeException("Car was not found"));
        return carConverter.toResponse(car);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarResponse updateCarPrice(Long id, Double price) {
        Car car;

        try {
            car = carRepository.findById(id).get();
        }
        catch (RuntimeException e) {
            throw  new RuntimeException(String.format("Car with %s not found",id));
        }
        car.setPricePerDay(price);

        return carConverter.toResponse(carRepository.save(car));
    }
}
