package com.baloot.baloot.domain.Baloot.Exceptions;

public class CommodityWithSameIDException extends Exception {
    public CommodityWithSameIDException() {
        super("Cant add new commodity. There is already a commodity with this id!");
    }
}
