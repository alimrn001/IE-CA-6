package com.baloot.baloot.Exceptions;

public class RatingOutOfRangeException extends Exception {
    public RatingOutOfRangeException() {
        super("Rating must be an integer between 1 and 10!");
    }
}
