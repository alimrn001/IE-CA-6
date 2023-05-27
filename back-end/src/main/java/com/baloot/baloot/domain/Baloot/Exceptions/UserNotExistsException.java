package com.baloot.baloot.domain.Baloot.Exceptions;

public class UserNotExistsException extends Exception {
    public UserNotExistsException() {
        super("The selected user does not exists!");
    }
}
