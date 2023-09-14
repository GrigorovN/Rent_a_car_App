package com.example.rentacarproject.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name",nullable = false)
    @NotBlank(message = "You must enter first name")
    @Size(min=2, message = "First name must be at least two characters")
    private String firstName;

    @Column(name = "last_name",nullable = false)
    @NotBlank(message = "You must enter last name")
    @Size(min=2, message = "Last name must be at least two characters")
    private String lastName;

    @Column(name = "email",nullable = false, unique = true)
    @NotBlank(message = "You must enter email")
    @Email(message = "You must enter a valid email")
    private String email;

    @Column(name = "password",nullable = false)
    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Reservation> reservations;

}
