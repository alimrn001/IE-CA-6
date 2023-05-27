package com.baloot.baloot.domain.Baloot.Exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("There is already a user with this username in system!");
    }
}
