package com.example.rentacarproject.converter;

import com.example.rentacarproject.dto.UserRequest;
import com.example.rentacarproject.dto.UserResponse;
import com.example.rentacarproject.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserConverter {
    public User toUser(UserRequest request){
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public UserResponse toResponse(User savedUser){
        return new UserResponse(
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName());
    }
}
