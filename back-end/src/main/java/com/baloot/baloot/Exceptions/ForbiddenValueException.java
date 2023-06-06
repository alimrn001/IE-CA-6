package com.baloot.baloot.Exceptions;

public class ForbiddenValueException extends Exception {
    public ForbiddenValueException() {
        super("Form fields must have valid values!");
    }
}
