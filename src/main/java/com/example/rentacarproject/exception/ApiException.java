package com.example.rentacarproject.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@ToString
public class ApiException {

    private final String message;

    private final HttpStatus httpStatus;

    private final ZonedDateTime zonedDateTime;


}
