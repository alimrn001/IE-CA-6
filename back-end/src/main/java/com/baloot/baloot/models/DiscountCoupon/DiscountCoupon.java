package com.baloot.baloot.models.DiscountCoupon;

import com.baloot.baloot.models.User.User;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class DiscountCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long discountCouponId;

    private String discountCode;

    private int discount;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = {@JoinColumn(name = "discountId")}, inverseJoinColumns = {@JoinColumn(name = "userId")})
    private Set<User> discountUsers = new HashSet<>();


    public DiscountCoupon() {}

    public DiscountCoupon(String discountCode, int discount) {
        this.discountCode = discountCode;
        this.discount = discount;
    }

    public void setDiscountCouponId(long discountCouponId) {
        this.discountCouponId = discountCouponId;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setDiscountUsers(Set<User> discountUsers) {
        this.discountUsers = discountUsers;
    }

    public long getDiscountCouponId() {
        return discountCouponId;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public int getDiscount() {
        return discount;
    }

    public Set<User> getDiscountUsers() {
        return discountUsers;
    }

}
