package com.baloot.baloot.domain.Baloot.DiscountCoupon;

import com.baloot.baloot.Exceptions.DiscountCouponNotExistsException;
import com.baloot.baloot.Exceptions.WrongDiscountPercentageException;

import java.util.HashMap;
import java.util.Map;

public class DiscountCouponsManager {

    private final Map<String, DiscountCoupon> balootDiscountCoupons = new HashMap<>();


    public boolean discountCouponExists(String discountCode) {
        return balootDiscountCoupons.containsKey(discountCode);
    }

    public void addDiscountCoupon(DiscountCoupon discountCoupon) throws Exception {
        if(discountCoupon.getDiscount() < 0 || discountCoupon.getDiscount() > 100)
            throw new WrongDiscountPercentageException();
        balootDiscountCoupons.put(discountCoupon.getDiscountCode(), discountCoupon);
    }

    public DiscountCoupon getDiscountCouponByCode(String discountCode) throws Exception {
        if(discountCouponExists(discountCode)) {
            return balootDiscountCoupons.get(discountCode);
        }
        throw new DiscountCouponNotExistsException();
    }

    public int getDiscountCouponValueByCode(String discountCode) throws Exception {
        return getDiscountCouponByCode(discountCode).getDiscount();
    }

    public Map<String, DiscountCoupon> getBalootDiscountCoupons() {
        return balootDiscountCoupons;
    }

}
