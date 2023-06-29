package com.example.rentacarproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarRequest {
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    private Double pricePerDay;
    private Integer seats;
}
