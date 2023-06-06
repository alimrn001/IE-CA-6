package com.baloot.baloot.Exceptions;

public class DiscountCouponNotExistsException extends Exception {
    public DiscountCouponNotExistsException() {
        super("There is no discount coupon with this code!");
    }
}
