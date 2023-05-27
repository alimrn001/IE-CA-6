package com.baloot.baloot.domain.Baloot.Exceptions;

public class DiscountCouponNotExistsException extends Exception {
    public DiscountCouponNotExistsException() {
        super("There is no discount coupon with this code!");
    }
}
