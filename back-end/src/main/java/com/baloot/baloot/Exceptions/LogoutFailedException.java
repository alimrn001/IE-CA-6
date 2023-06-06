package com.baloot.baloot.Exceptions;

public class LogoutFailedException extends Exception {
    public LogoutFailedException() {
        super("Failed to logout! No user is logged in!");
    }
}
