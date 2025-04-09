package com.example.wigelladminapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {

    private final String message;
    private final Object value;

    public UnprocessableEntityException(String message, Object value) {
        super(String.format("Unprocessable Entity: %s. Input: %s", message, value));
        this.message = message;
        this.value = value;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Object getValue() {
        return value;
    }
}
