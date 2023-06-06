package com.baloot.baloot.DTO;

public class BuyListItemCntDTO {

    private int commodityID;

    private int cartItemsCount;

    public BuyListItemCntDTO(int commodityID, int cartItemsCount) {
        this.commodityID = commodityID;
        this.cartItemsCount = cartItemsCount;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public void setCartItemsCount(int cartItemsCount) {
        this.cartItemsCount = cartItemsCount;
    }

    public int getCommodityID() {
        return commodityID;
    }

    public int getCartItemsCount() {
        return cartItemsCount;
    }

}
