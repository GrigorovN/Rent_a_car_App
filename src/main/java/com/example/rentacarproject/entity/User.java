package com.example.rentacarproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Builder
@Entity (name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(length = 30)
    @NotBlank
    private String firstName;
    @Column(length = 30)
    @NotBlank
    private String lastName;
    @Column(unique = true)
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Reservation> reservations;

}
