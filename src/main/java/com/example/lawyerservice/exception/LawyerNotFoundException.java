package com.example.lawyerservice.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such Lawyer in Repository")
public class LawyerNotFoundException extends EntityNotFoundException {

    private String message;
    public LawyerNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
