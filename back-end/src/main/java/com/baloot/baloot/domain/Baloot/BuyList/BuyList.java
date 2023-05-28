package com.baloot.baloot.domain.Baloot.BuyList;

import java.util.ArrayList;

public class BuyList {

    private String buyListId; //'username'+ '_' + '0 or 1 or ...'

    private String username;

    private ArrayList<Integer> items;

    private String discountCouponName;

    private int discountPercentage;

    private int totalPrice; //without discount

    private int finalPrice;


    public BuyList(String buyListId, String username) {
        this.buyListId = buyListId;
        this.username = username;
        this.items = new ArrayList<>();
        this.discountCouponName = "";
        this.discountPercentage = 0;
        this.totalPrice = 0;
        this.finalPrice = 0;
    }

    public void addItemToBuyList(int buyListItemGeneralId, int price) {
        items.add(buyListItemGeneralId);
        totalPrice += price;
    }

    public void addDiscountCouponToBuyList(String discountCouponName, int discountPercentage) {
        this.discountCouponName = discountCouponName;
        this.discountPercentage = discountPercentage;
    }

    public void setBuyListId(String buyListId) {
        this.buyListId = buyListId;
    }

    public void setDiscountCouponName(String discountCouponName) {
        this.discountCouponName = discountCouponName;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setItems(ArrayList<Integer> items) {
        this.items = items;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBuyListId() {
        return buyListId;
    }

    public ArrayList<Integer> getItems() {
        return items;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getDiscountCouponName() {
        return discountCouponName;
    }

    public String getUsername() {
        return username;
    }

    public int getFinalPrice() {
        this.finalPrice = totalPrice * (1-(discountPercentage/100));
        return this.finalPrice;
    }

}
