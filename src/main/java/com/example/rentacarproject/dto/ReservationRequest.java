package com.example.rentacarproject.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservationRequest {
    private LocalDate dateIn;

    private LocalDate dateOut;
    private Long userId;
    private Long carId;

}
