package com.dev.UserService.Exception;

public class UserNotfoundException extends RuntimeException{

    public UserNotfoundException() {
    }

    public UserNotfoundException(String message) {
        super(message);
    }
}
