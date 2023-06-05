package com.baloot.baloot.DTO;

import java.util.ArrayList;

public class CommodityDTO {

    private int id;

    private String name;

    private int providerId;

    private int price;

    private ArrayList<String> categories = new ArrayList<>();

    private double rating;

    private int inStock;

    private String image;

    private int numOfRatings;

    private ArrayList<Integer> comments = new ArrayList<>();


    public CommodityDTO(int id, String name, int providerId, int price, double rating, int inStock, String image, int numOfRatings) {
        this.id = id;
        this.name = name;
        this.providerId = providerId;
        this.price = price;
        this.rating = rating;
        this.image = image;
        this.inStock = inStock;
        this.numOfRatings = numOfRatings;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public void setComments(ArrayList<Integer> comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getProviderId() {
        return providerId;
    }

    public int getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    public int getInStock() {
        return inStock;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<Integer> getComments() {
        return comments;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

}
