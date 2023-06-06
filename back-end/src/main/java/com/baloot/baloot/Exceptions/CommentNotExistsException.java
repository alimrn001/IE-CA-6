package com.baloot.baloot.Exceptions;

public class CommentNotExistsException extends Exception {
    public CommentNotExistsException() {
        super("The selected comment does not exist!");
    }
}
