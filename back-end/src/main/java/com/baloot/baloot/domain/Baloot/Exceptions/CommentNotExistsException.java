package com.baloot.baloot.domain.Baloot.Exceptions;

public class CommentNotExistsException extends Exception {
    public CommentNotExistsException() {
        super("The selected comment does not exist!");
    }
}
