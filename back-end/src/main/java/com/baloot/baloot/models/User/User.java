package com.baloot.baloot.models.User;
import com.baloot.baloot.models.BuyList.BuyList;
import com.baloot.baloot.models.BuyList.BuyListItem;
import com.baloot.baloot.models.Comment.Comment;
import com.baloot.baloot.models.DiscountCoupon.DiscountCoupon;
import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    private String username;

    private String password;

    private LocalDate birthDate;

    @Column(unique = true)
    private String email;

    private String address;

    private int credit;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "discountUsers")
    private Set<DiscountCoupon> usedDiscounts = new HashSet<>();


    public User() {}

    public User(String username, String password, LocalDate birthDate, String email, String address, int credit) {
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
        this.credit = credit;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setUsedDiscounts(Set<DiscountCoupon> usedDiscounts) {
        this.usedDiscounts = usedDiscounts;
    }

    public void addCredit(int amount) {
        this.credit += amount;
    }

    public void reduceCredit(int amount) {
        this.credit -= amount;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public int getCredit() {
        return credit;
    }

    public Set<DiscountCoupon> getUsedDiscounts() {
        return usedDiscounts;
    }

}
