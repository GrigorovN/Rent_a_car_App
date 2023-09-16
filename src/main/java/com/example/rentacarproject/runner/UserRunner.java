package com.example.rentacarproject.runner;

import com.example.rentacarproject.dto.UserRequest;
import com.example.rentacarproject.repository.UserRepository;
import com.example.rentacarproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRunner implements CommandLineRunner {

    private final UserRepository userRepository;

    private final UserService userService;


    public UserRunner(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        // creating some initial users
        createUser("gosho@abv.bg", "Gosho", "Georgiev", "test12345");
        createUser("pesho@abv.bg", "Pesho", "Mitkov", "random453");
        createUser("ivan@abv.bg", "Ivan", "Georgiev", "password");
        createUser("rangel@abv.bg", "Rangel", "Ivanov", "Adm!n435");
        createUser("peter@abv.bg", "Peter", "Griffin", "teessstts");


    }

    private void createUser (String email, String firstName, String lastName, String password){

        UserRequest userRequest = UserRequest.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .password(password)
                .build();

        if (userRepository.findByEmail(email).isEmpty()) {
            userService.saveUser(userRequest);
        }
    }
}
