package com.example.wigelladminapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NotUniqException extends RuntimeException {

    private final String field;
    private final Object value;

    public NotUniqException(String field, Object value) {
        super(String.format(field + " [" + value + "]" + " is already taken"));
        this.field = field;
        this.value = value;
    }
}
