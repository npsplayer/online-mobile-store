package com.email.npsplayer00.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RegistrationException extends RuntimeException {

    public RegistrationException() {
    }

    public RegistrationException(String message) {
        super(message);
    }


}