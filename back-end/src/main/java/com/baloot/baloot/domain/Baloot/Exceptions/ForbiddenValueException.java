package com.baloot.baloot.domain.Baloot.Exceptions;

public class ForbiddenValueException extends Exception {
    public ForbiddenValueException() {
        super("Form fields must have valid values!");
    }
}
