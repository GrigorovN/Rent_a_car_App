package com.example.rentacarproject.controller;

import com.example.rentacarproject.dto.UserRequest;
import com.example.rentacarproject.dto.UserResponse;
import com.example.rentacarproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path ="api/v2/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest request) {
        UserResponse response = userService.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userService.getUser(id));
    }

    @DeleteMapping(path = "/{id}")
    public HttpStatus deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return HttpStatus.ACCEPTED;
    }

    @GetMapping(path = "/find")
    public ResponseEntity<UserResponse> getUserByEmail(@RequestParam String email){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userService.getUserByEmail(email));
    }
    /** With this method you can update all fields of the User except the id.
     * @author Nikola Grigorov */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        UserResponse userResponse = userService.updateUserDetails(id,request);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
    /** This method is to update only the password of the user
     * @author Nikola Grigorov */
    @PutMapping("/pass")
    public ResponseEntity<UserResponse> updatePassword (@RequestParam Long id, @RequestParam String pass) {
        UserResponse userResponse = userService.updateUserPassword(id, pass);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }
}
