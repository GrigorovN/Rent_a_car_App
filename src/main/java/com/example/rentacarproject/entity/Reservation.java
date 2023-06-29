package com.example.rentacarproject.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long stays;
    private LocalDate dateIn;
    private LocalDate dateOut;
    private Double totalPrice;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;
    @ManyToOne
    @JsonManagedReference
    private Car car;

}
