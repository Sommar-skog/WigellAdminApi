package com.example.wigelladminapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {

    private String message;
    private Object value;

    public UnprocessableEntityException(String message, Object value) {
        super(String.format("Unprocessable Entity: %s. Input: %s", message, value));
        this.message = message;
        this.value = value;
    }
}
