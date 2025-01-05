package com.dev.UserService.Exception;

public class InvalidCredentialException extends RuntimeException{

    public InvalidCredentialException() {
    }

    public InvalidCredentialException(String message) {
        super(message);
    }

}
