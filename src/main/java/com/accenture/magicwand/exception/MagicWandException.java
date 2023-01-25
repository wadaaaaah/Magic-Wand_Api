package com.accenture.magicwand.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MagicWandException extends RuntimeException{

    public static final String INVALID_ID = "Magic ID not found";

    public MagicWandException(String errorMessage){
        super(errorMessage);
    }
}
