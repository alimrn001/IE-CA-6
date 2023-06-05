package com.baloot.baloot.domain.Baloot.Exceptions;

public class WrongVoteValueException extends Exception {
    public WrongVoteValueException() {
        super("Value of vote for a comment must be 0 or 1 !");
    }
}
