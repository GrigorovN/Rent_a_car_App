package com.example.rentacarproject.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRequest {

    @NotBlank
    @Length(min = 2,message = "Name should contain at least 2 characters")
    private String firstName;

    @NotBlank
    @Length(min = 2 ,message = "Last Name should contains at least 2 characters")
    private String lastName;

    @NotNull
    @Email(message = "Enter valid email")
    private String email;

    @NotNull
    @Length(min = 8, message = "Password should contains at least 8 characters")
    private String password;
}
