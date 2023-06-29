package com.example.rentacarproject.service;

import com.example.rentacarproject.dto.UserRequest;
import com.example.rentacarproject.dto.UserResponse;



public interface UserService {
    UserResponse saveUser(UserRequest request);
    UserResponse getUser(Long id);
    UserResponse getUserByEmail(String email);
    void deleteUser(Long id);
    UserResponse updateUserDetails(Long id, UserRequest request);
    UserResponse updateUserPassword(Long id, String pass);
}
