package com.example.rentacarproject.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReservationRequest {

    @NotNull
    @FutureOrPresent
    private LocalDate dateIn;

    @NotNull
    @Future
    private LocalDate dateOut;

    @NotNull
    private Long userId;

    @NotNull
    private Long carId;

}
