package com.baloot.baloot.domain.Baloot.Exceptions;

public class UsernameWrongCharacterException extends Exception {
    public UsernameWrongCharacterException() {
        super("Username can not contain characters like # @ ! ....");
    }
}
