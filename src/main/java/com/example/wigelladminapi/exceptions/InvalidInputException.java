package com.example.wigelladminapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputException  extends RuntimeException{

    private final String object;
    private final String field;
    private final Object value;

    public InvalidInputException(String object, String field, Object value) {
        super(String.format("Invalid input: " + object + " [" + field + "] cannot  be " + value));
        this.object = object;
        this.field = field;
        this.value = value;
    }

    public String getObject() {
        return object;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
