package com.example.rentacarproject.dto;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserRequest {

    @NotBlank
    @Length(min = 2,message = "Name should contain at least 2 characters")
    private String firstName;

    @NotBlank
    @Length(min = 2 ,message = "Last Name should contains at least 2 characters")
    private String lastName;
    @Email(message = "Enter valid email")
    private String email;
    @Length(min = 6, message = "Password should contains at least 6 characters")
    private String password;
}
