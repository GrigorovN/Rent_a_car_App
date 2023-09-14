package com.example.rentacarproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "stays",nullable = false)
    @NotNull
    @Positive
    private Long stays;

    @Column(name = "date_in",nullable = false)
    @NotNull
    @FutureOrPresent
    private LocalDate dateIn;

    @Column(name = "date_out",nullable = false)
    @NotNull
    @Future
    private LocalDate dateOut;

    @Column(name = "total_price",nullable = false)
    @NotNull
    @Positive
    private Double totalPrice;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JsonManagedReference
    private Car car;

}
