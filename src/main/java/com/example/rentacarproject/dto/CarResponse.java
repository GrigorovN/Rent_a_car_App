package com.example.rentacarproject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CarResponse {

    private String brand;
    private String model;
    private Double pricePerDay;
}
