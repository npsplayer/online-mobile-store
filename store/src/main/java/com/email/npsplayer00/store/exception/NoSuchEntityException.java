package com.email.npsplayer00.store.exception;

public class NoSuchEntityException extends RuntimeException{

    public NoSuchEntityException() {
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}
