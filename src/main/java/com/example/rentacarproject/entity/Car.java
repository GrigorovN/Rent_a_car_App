package com.example.rentacarproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand", nullable = false)
    @NotBlank(message = "You must enter  car brand")
    private String brand;

    @Column(name = "model", nullable = false)
    @NotBlank(message = "You must enter car model")
    private String model;

    @Column(name = "price_per_day", nullable = false)
    @NotNull(message = "You must enter a price")
    @Positive
    private Double pricePerDay;

    @Column(name = "seats", nullable = false)
    @NotNull(message = "You should enter seats")
    @Min(value = 2)
    private Integer seats;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Reservation> reservations;


}
