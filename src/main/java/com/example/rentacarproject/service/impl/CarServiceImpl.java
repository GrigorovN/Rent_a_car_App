package com.example.rentacarproject.service.impl;

import com.example.rentacarproject.converter.CarConverter;
import com.example.rentacarproject.dto.CarRequest;
import com.example.rentacarproject.dto.CarResponse;
import com.example.rentacarproject.entity.Car;
import com.example.rentacarproject.exception.ApiRequestException;
import com.example.rentacarproject.exception.NotFoundException;
import com.example.rentacarproject.repository.CarRepository;
import com.example.rentacarproject.service.CarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarConverter carConverter;

    public CarServiceImpl(CarRepository carRepository, CarConverter carConverter) {

        this.carRepository = carRepository;
        this.carConverter = carConverter;

    }

    @Override
    public CarResponse saveCar(@Valid CarRequest carRequest) {

       Car car = carConverter.toCar(carRequest);
       return carConverter.toResponse(carRepository.save(car));

    }

    @Override
    public CarResponse getCar(Long id) {

        Car car = carRepository.findById(id).orElseThrow( () -> new NotFoundException("Car not found with ID: " + id));
        return carConverter.toResponse(car);
    }

    @Override
    public void deleteCar(Long id) {

        try {
            carRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiRequestException("Failed to delete car with ID: " + id, e);
        }
    }

    @Override
    public CarResponse updateCarPrice(Long id, Double price) {

        Optional<Car> optionalCar = carRepository.findById(id);
        Car car = optionalCar.orElseThrow(() -> new NotFoundException("Car not found with ID: " + id));

        car.setPricePerDay(price);

        return carConverter.toResponse(carRepository.save(car));
    }

    @Override
    public List<CarResponse> findCarsByBrand(String brand) {

        List<Car> cars = carRepository.findByBrand(brand);

        return cars.stream()
                .map(carConverter::toResponse)
                .collect(Collectors.toList());
    }
}
