package com.example.rentacarproject.dto;

import com.example.rentacarproject.entity.Car;
import com.example.rentacarproject.entity.User;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservationResponse {
    private Long id;
    private Long stays;
    private LocalDate dateIn;
    private LocalDate dateOut;

    private Double totalPrice;
    private Car car;
    private User user;
}
