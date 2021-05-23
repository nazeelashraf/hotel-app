package com.hotel.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CouldNotFindException extends RuntimeException {
    public CouldNotFindException(String fields) {
        super(String.join(" ", "Could not find", fields));
    }
}
