package com.baloot.baloot.domain.Baloot.BuyList;

public class BuyListItem {

    private int generalId; // now sure whether this would be needed or not, its a general primary key for any item in buy list and

    private String buyListId;

    private int commodityId;

    private int buyListItemNumber;

    private int itemPrice;

    private int quantity;

    private int totalPrice;


    public BuyListItem(int generalId, String buyListId, int commodityId, int buyListItemNumber, int itemPrice, int quantity) {
        this.generalId = generalId;
        this.buyListId = buyListId;
        this.commodityId = commodityId;
        this.buyListItemNumber = buyListItemNumber;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.totalPrice = this.itemPrice * this.quantity;
    }

    public void setGeneralId(int generalId) {
        this.generalId = generalId;
    }

    public void setBuyListId(String buyListId) {
        this.buyListId = buyListId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public void setBuyListItemNumber(int buyListItemNumber) {
        this.buyListItemNumber = buyListItemNumber;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
        this.updateTotalPrice();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.updateTotalPrice();
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    } //probably won't be needed

    public void updateTotalPrice() {
        this.totalPrice = this.itemPrice * this.quantity;
    }

    public int getGeneralId() {
        return generalId;
    }

    public String getBuyListId() {
        return buyListId;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public int getBuyListItemNumber() {
        return buyListItemNumber;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void incrementItemQuantity(int n) {
        this.quantity += n;
        this.updateTotalPrice();
    }//probably won't be needed

}
