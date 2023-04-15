package com.example.swi1project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RecordNotFoundException extends ResponseStatusException{
    public RecordNotFoundException(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
