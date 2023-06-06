package com.baloot.baloot.Exceptions;

public class ProviderNotExistsException extends Exception {
    public ProviderNotExistsException() {
        super("The selected provider does not exists!");
    }
}
