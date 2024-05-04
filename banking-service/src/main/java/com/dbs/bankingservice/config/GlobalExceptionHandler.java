package com.dbs.bankingservice.config;

import com.dbs.bankingservice.dto.ServiceResponse;
import com.dbs.bankingservice.exceptions.UserAccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserAccountNotFoundException.class)
    public ServiceResponse userAccountNotFound(UserAccountNotFoundException e) {
        return new ServiceResponse(HttpStatus.BAD_REQUEST,e.getMessage());
    }
}
