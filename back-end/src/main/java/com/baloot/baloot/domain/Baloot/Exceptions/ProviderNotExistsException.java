package com.baloot.baloot.domain.Baloot.Exceptions;

public class ProviderNotExistsException extends Exception {
    public ProviderNotExistsException() {
        super("The selected provider does not exists!");
    }
}
