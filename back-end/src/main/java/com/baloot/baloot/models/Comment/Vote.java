package com.baloot.baloot.models.Comment;

import com.baloot.baloot.models.Commodity.Commodity;
import com.baloot.baloot.models.User.User;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@IdClass(VoteKey.class)
public class Vote {

    @Id
    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comment comment;

    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private int vote; // 0 for dislike, 1 for like

    public Vote(Comment comment, User user, int vote) {
        this.comment = comment;
        this.user = user;
        this.vote = vote;
    }

    public Vote() {}

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public Comment getComment() {
        return comment;
    }

    public User getUser() {
        return user;
    }

    public int getVote() {
        return vote;
    }

}
