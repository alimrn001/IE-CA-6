package com.baloot.baloot.models.Comment;

import com.baloot.baloot.models.Commodity.Commodity;
import com.baloot.baloot.models.User.User;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;

    @SerializedName(value = "userEmail") //needed ?
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "COMMODITY_ID")
    private Commodity commodity;

//    private int commodityId;

    private String text;

    private LocalDate date;

    private int likesNo;

    private int dislikesNo;


    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    public void setLikesNo(int likesNo) {
        this.likesNo = likesNo;
    }

    public void setDislikesNo(int dislikesNo) {
        this.dislikesNo = dislikesNo;
    }

    public void addLike() {
        likesNo++;
    }

    public void removeLike() {
        likesNo--;
    }

    public void addDislike() {
        dislikesNo++;
    }

    public void removeDislike() {
        dislikesNo--;
    }

    public int getCommentId() {
        return commentId;
    }

    public User getUser() {
        return user;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getLikesNo() {
        return likesNo;
    }

    public int getDislikesNo() {
        return dislikesNo;
    }

}
