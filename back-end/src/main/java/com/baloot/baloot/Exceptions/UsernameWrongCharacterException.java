package com.baloot.baloot.Exceptions;

public class UsernameWrongCharacterException extends Exception {
    public UsernameWrongCharacterException() {
        super("Username can not contain characters like # @ ! ....");
    }
}
