package com.baloot.baloot.domain.Baloot.Exceptions;

public class LoginFailedException extends Exception {
    public LoginFailedException() {
        super("Failed to login! Wrong username or password!");
    }
}
