package com.example.rentacarproject.service.impl;

import com.example.rentacarproject.converter.UserConverter;
import com.example.rentacarproject.dto.UserRequest;
import com.example.rentacarproject.dto.UserResponse;
import com.example.rentacarproject.entity.User;
import com.example.rentacarproject.exception.ApiRequestException;
import com.example.rentacarproject.exception.NotFoundException;
import com.example.rentacarproject.repository.UserRepository;
import com.example.rentacarproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;

    public UserServiceImpl(UserConverter userConverter, UserRepository userRepository) {
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse saveUser(UserRequest request) {

        if (existUserByEmail(request.getEmail())){
            throw new ApiRequestException(String.format("User with email: %s already exists",request.getEmail()));
        }

        User user = userConverter.toUser(request);
        User savedUser = userRepository.save(user);

        return userConverter.toResponse(savedUser);
    }

    @Override
    public UserResponse getUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("User with id = %d not found", id)));

        return userConverter.toResponse(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User with this email is not found"));

        return userConverter.toResponse(user);
    }

    @Override
    public void deleteUser(Long id) {

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ApiRequestException("Failed to delete user with ID: " + id, e);
        }

    }

    @Override
    public UserResponse updateUserDetails(Long id, @Valid UserRequest request) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("User with id = %d not found", id)));

        // Validate first name
        if (request.getFirstName() != null && request.getFirstName().length() < 2) {
            throw new ApiRequestException("First name should contain at least 2 characters");
        }

        // Validate last name
        if (request.getLastName() != null && request.getLastName().length() < 2) {
            throw new ApiRequestException("Last name should contain at least 2 characters");
        }

        // Validate password
        if (request.getPassword() != null && request.getPassword().length() < 8) {
            throw new ApiRequestException("Password must be at least 8 characters long");
        }

        updateUserFields(user, request);

        return userConverter.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUserPassword(Long id, String pass) {

        UserRequest userRequest = new UserRequest();
        userRequest.setPassword(pass);

        return updateUserDetails(id, userRequest);
    }

    private void updateUserFields(User user, UserRequest request) {

        if (Objects.nonNull(request.getFirstName()) && !request.getFirstName().isBlank()) {
            user.setFirstName(request.getFirstName());
        }
        if (Objects.nonNull(request.getLastName()) && !request.getLastName().isBlank()) {
            user.setLastName(request.getLastName());
        }
        if (Objects.nonNull(request.getPassword()) && !request.getPassword().isBlank()) {
            user.setPassword(request.getPassword());
        }
        if (Objects.nonNull(request.getEmail()) && !request.getEmail().isBlank()) {
            user.setEmail(request.getEmail());
        }
    }

    private boolean existUserByEmail(String email) {
        return userRepository.findAll().stream().anyMatch(user -> user.getEmail().equals(email));
    }

}
