package com.baloot.baloot.Exceptions;

public class UserNotExistsException extends Exception {
    public UserNotExistsException() {
        super("The selected user does not exists!");
    }
}
