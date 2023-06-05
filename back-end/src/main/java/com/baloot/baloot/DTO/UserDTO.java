package com.baloot.baloot.DTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class UserDTO {

    String username;

    private String password;

    private LocalDate birthDate;

    private String email;

    private String address;

    private int credit;

    private String latestSelectedDiscountCode;

    private ArrayList<Integer> buyList;

    private ArrayList<Integer> commentsList;

    private ArrayList<Integer> purchasedList;

    private ArrayList<Integer> likedComments;

    private ArrayList<Integer> dislikedComments;

    private ArrayList<String> usedDiscountCoupons;


    public UserDTO(String username, String password, LocalDate birthDate, String email, String address, int credit) {
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
        this.credit = credit;
        buyList = new ArrayList<>();
        commentsList = new ArrayList<>();
        purchasedList = new ArrayList<>();
        likedComments = new ArrayList<>();
        dislikedComments = new ArrayList<>();
        usedDiscountCoupons = new ArrayList<>();
        latestSelectedDiscountCode = "";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public void setBuyList(ArrayList<Integer> buyList) {
        this.buyList = buyList;
    }

    public void setDislikedComments(ArrayList<Integer> dislikedComments) {
        this.dislikedComments = dislikedComments;
    }

    public void setLikedComments(ArrayList<Integer> likedComments) {
        this.likedComments = likedComments;
    }

    public void setUsedDiscountCoupons(ArrayList<String> usedDiscountCoupons) {
        this.usedDiscountCoupons = usedDiscountCoupons;
    }

    public void setCommentsList(ArrayList<Integer> commentsList) {
        this.commentsList = commentsList;
    }

    public void setPurchasedList(ArrayList<Integer> purchasedList) {
        this.purchasedList = purchasedList;
    }

    public void setLatestSelectedDiscountCode(String latestSelectedDiscountCode) {
        this.latestSelectedDiscountCode = latestSelectedDiscountCode;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public int getCredit() {
        return credit;
    }

    public ArrayList<String> getUsedDiscountCoupons() {
        return usedDiscountCoupons;
    }

    public ArrayList<Integer> getBuyList() {
        return buyList;
    }

    public ArrayList<Integer> getCommentsList() {
        return commentsList;
    }

    public ArrayList<Integer> getDislikedComments() {
        return dislikedComments;
    }

    public ArrayList<Integer> getLikedComments() {
        return likedComments;
    }

    public ArrayList<Integer> getPurchasedList() {
        return purchasedList;
    }

    public String getLatestSelectedDiscountCode() {
        return latestSelectedDiscountCode;
    }

}
