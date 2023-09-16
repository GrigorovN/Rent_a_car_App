package com.example.rentacarproject.repository;

import com.example.rentacarproject.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findByModel(String model);

    List<Car> findByBrand(String brand);
}
