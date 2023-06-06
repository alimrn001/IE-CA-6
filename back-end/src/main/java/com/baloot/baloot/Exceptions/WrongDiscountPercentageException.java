package com.baloot.baloot.Exceptions;

public class WrongDiscountPercentageException extends Exception {
    public WrongDiscountPercentageException() {
        super("The amount of coupon discount must be between 0 and 100 !");
    }
}
