package com.example.rentacarproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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

    @NotNull
    @PositiveOrZero
    private Double pricePerDay;

    @NotNull
    @Positive
    private Integer seats;
}
