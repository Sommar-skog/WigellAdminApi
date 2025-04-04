package com.example.wigelladminapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoResultException extends RuntimeException {

    private String object;

    public NoResultException (String object){
        super(String.format("No result found for " + object));
        this.object = object;
    }
}
