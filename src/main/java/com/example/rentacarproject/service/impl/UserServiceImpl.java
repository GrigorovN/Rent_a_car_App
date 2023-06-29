package com.example.rentacarproject.service.impl;

import com.example.rentacarproject.converter.UserConverter;
import com.example.rentacarproject.dto.UserRequest;
import com.example.rentacarproject.dto.UserResponse;
import com.example.rentacarproject.entity.User;
import com.example.rentacarproject.repository.UserRepository;
import com.example.rentacarproject.service.UserService;
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
        User user = userConverter.toUser(request);
        User savedUser = userRepository.save(user);
        return userConverter.toResponse(savedUser);
    }

    @Override
    public UserResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("User not found"));
        return userConverter.toResponse(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new RuntimeException("User not found"));
        return userConverter.toResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse updateUserDetails(Long id, UserRequest request) {
        User user;

        try {
            user = userRepository.findById(id).get();
        }
        catch (RuntimeException e) {
            throw  new RuntimeException(String.format("User with %s not found",id));
        }

        if (Objects.nonNull(request.getFirstName()) && !request.getFirstName().isBlank()){
            user.setFirstName(request.getFirstName());
        }
        if (Objects.nonNull(request.getLastName()) && !request.getLastName().isBlank()){
            user.setLastName(request.getLastName());
        }
        if (Objects.nonNull(request.getPassword()) && !request.getPassword().isBlank()){
            user.setPassword(request.getPassword());
        }
        if (Objects.nonNull(request.getEmail()) && !request.getEmail().isBlank()){
            user.setEmail(request.getEmail());
        }
        return userConverter.toResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUserPassword(Long id, String pass) {
        UserRequest userRequest = new UserRequest();
        userRequest.setPassword(pass);
         return updateUserDetails(id,userRequest);
    }
}
