package com.baloot.baloot.domain.Baloot.Exceptions;

public class CommodityNotExistsException extends Exception {
    public CommodityNotExistsException() {
        super("There is not a commodity with this ID!");
    }
}
