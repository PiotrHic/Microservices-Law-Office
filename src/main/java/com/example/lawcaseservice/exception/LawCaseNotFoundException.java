package com.example.lawcaseservice.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such LawCase in Repository")
public class LawCaseNotFoundException extends EntityNotFoundException {

    private String message;

    public LawCaseNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
