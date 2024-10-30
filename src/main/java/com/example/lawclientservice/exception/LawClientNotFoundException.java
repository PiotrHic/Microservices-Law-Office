package com.example.lawclientservice.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such LawClient in Repository")
public class LawClientNotFoundException extends EntityNotFoundException {

    private String message;

    public LawClientNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
