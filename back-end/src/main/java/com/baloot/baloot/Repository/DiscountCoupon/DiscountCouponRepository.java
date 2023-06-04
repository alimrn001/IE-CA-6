package com.baloot.baloot.Repository.DiscountCoupon;

import com.baloot.baloot.models.DiscountCoupon.DiscountCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountCouponRepository extends JpaRepository<DiscountCoupon, Long> {

    DiscountCoupon getDiscountCouponByDiscountCouponId(long discountCouponId);

    DiscountCoupon getDiscountCouponByDiscountCode(String discountCode);

    List<DiscountCoupon> findDistinctByDiscountUsers_Username(String username);

}
