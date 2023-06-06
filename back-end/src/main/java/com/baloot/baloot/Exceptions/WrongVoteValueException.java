package com.baloot.baloot.Exceptions;

public class WrongVoteValueException extends Exception {
    public WrongVoteValueException() {
        super("Value of vote for a comment must be -1 or 1 !");
    }
}
