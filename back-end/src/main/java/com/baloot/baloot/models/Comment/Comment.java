package com.baloot.baloot.models.Comment;

import com.baloot.baloot.models.Commodity.Commodity;
import com.baloot.baloot.models.User.User;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;

    @SerializedName(value = "userEmail") //needed ?
    @Transient
    private String username;

    @Transient
    private int commodityId;

    private String text;

    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "commodity_id")
    private Commodity commodity;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "commentId")
    private Set<Vote> votes = new HashSet<>();


    public Comment() {}

    public Comment(String username, int commodityId, String text, String date) {
        this.username=username;
        this.commodityId=commodityId;
        this.text=text;
        this.date=date;
    }

    public Comment(User user, Commodity commodity, String text, String date) {
        this.user=user;
        this.commodity = commodity;
        this.text = text;
        this.date = date;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setVotes(Set<Vote> votes) {
        this.votes = votes;
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

    public int getCommentId() {
        return commentId;
    }

    public String getUsername() {
        return username;
    }

    public int getCommodityId() {
        return commodityId;
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

    public String getDate() {
        return date;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

}
