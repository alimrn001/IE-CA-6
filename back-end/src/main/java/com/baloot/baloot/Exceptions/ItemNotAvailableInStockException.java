package com.baloot.baloot.Exceptions;

public class ItemNotAvailableInStockException extends Exception {
    public ItemNotAvailableInStockException() {
        super("The selected item is not available in stock!");
    }
}
