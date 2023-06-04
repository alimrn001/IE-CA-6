package com.baloot.baloot.models.Rating;

import com.baloot.baloot.models.Commodity.Commodity;
import com.baloot.baloot.models.User.User;
import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@IdClass(RatingKey.class)
public class Rating {
    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "id")
    private Commodity commodity;

    @Column(nullable = false)
    private int score;


    public Rating() {}

    public Rating(User user, Commodity commodity, int score) {
        this.user = user;
        this.commodity = commodity;
        this.score = score;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public Commodity getCommodity() {
        return commodity;
    }

    public int getScore() {
        return score;
    }

}
