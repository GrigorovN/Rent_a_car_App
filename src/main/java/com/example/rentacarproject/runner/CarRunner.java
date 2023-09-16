package com.example.rentacarproject.runner;

import com.example.rentacarproject.dto.CarRequest;
import com.example.rentacarproject.repository.CarRepository;
import com.example.rentacarproject.service.CarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CarRunner implements CommandLineRunner {

    private final CarRepository carRepository;

    private final CarService carService;

    public CarRunner(CarRepository carRepository, CarService carService) {
        this.carRepository = carRepository;
        this.carService = carService;
    }

    @Override
    public void run(String... args) throws Exception {

        createCar("BMW", "X3", 5, 120.0);
        createCar("BMW", "X5", 5, 150.0);
        createCar("Audi", "a3", 4, 89.0);
        createCar("Volkswagen", "Up", 2, 60.0);
        createCar("Renault", "Clio", 4, 70.0);

    }

    private void createCar(String brand, String model, Integer seats, Double price) {

        CarRequest carRequest = CarRequest.builder()
                .brand(brand)
                .model(model)
                .seats(seats)
                .pricePerDay(price)
                .build();

        if (carRepository.findByModel(model).isEmpty()) {
            carService.saveCar(carRequest);
        }

    }
}
