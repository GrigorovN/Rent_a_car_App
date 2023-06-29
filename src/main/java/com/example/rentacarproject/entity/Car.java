package com.example.rentacarproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cars")
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @NotBlank
    private String brand;
    @NotBlank
    private String model;
    private Double pricePerDay;
    private Integer seats;
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Reservation> reservations;


}
