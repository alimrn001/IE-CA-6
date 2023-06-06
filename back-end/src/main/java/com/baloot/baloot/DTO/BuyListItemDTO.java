package com.baloot.baloot.DTO;

import java.util.ArrayList;

public class BuyListItemDTO {

    private long itemId;

    private int commodityID;

    private int quantity;

    private int providerID;

    private String productName;

    private ArrayList<String> categories;

    private int price;

    private double rating;

    private int inStock;

    private String img;


    public BuyListItemDTO(long itemId, int commodityID, int quantity, int providerID, String productName, ArrayList<String> categories, int price, double rating, int inStock, String img) {
        this.itemId = itemId;
        this.commodityID = commodityID;
        this.quantity = quantity;
        this.providerID = providerID;
        this.productName = productName;
        this.categories = categories;
        this.price = price;
        this.rating = rating;
        this.inStock = inStock;
        this.img = img;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public void setCommodityID(int commodityID) {
        this.commodityID = commodityID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProviderID(int providerID) {
        this.providerID = providerID;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getItemId() {
        return itemId;
    }

    public int getCommodityID() {
        return commodityID;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProviderID() {
        return providerID;
    }

    public String getProductName() {
        return productName;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public int getInStock() {
        return inStock;
    }

    public double getRating() {
        return rating;
    }

    public int getPrice() {
        return price;
    }

    public String getImg() {
        return img;
    }

}
