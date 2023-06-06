package com.baloot.baloot.Exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        super("There is already a user with this username in system!");
    }
}
