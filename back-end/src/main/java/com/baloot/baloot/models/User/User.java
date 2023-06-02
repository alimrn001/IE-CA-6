package com.baloot.baloot.models.User;
import com.baloot.baloot.models.Comment.Comment;
import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @OneToMany //(mappedBy = "user") //not sure
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Comment> postedComments;

    @OneToMany //(mappedBy = "user") //not sure
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Comment> likedComments;

    @OneToMany //(mappedBy = "user") //not sure
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Comment> dislikedComments;

//    private ArrayList<Integer> buyList;

//    private ArrayList<Integer> purchasedList;

//    private ArrayList<String> usedDiscountCoupons;


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

    public void setPostedComments(Set<Comment> postedComments) {
        this.postedComments = postedComments;
    }

    public void setLikedComments(Set<Comment> likedComments) {
        this.likedComments = likedComments;
    }

    public void setDislikedComments(Set<Comment> dislikedComments) {
        this.dislikedComments = dislikedComments;
    }

    public void addToPostedComments(Comment comment) {
        postedComments.add(comment);
    }

    public void addToLikedComments(Comment comment) {
        likedComments.add(comment);
    }

    public void addToDislikedComments(Comment comment) {
        dislikedComments.add(comment);
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

    public Set<Comment> getPostedComments() {
        return postedComments;
    }

    public Set<Comment> getLikedComments() {
        return likedComments;
    }

    public Set<Comment> getDislikedComments() {
        return dislikedComments;
    }

}
