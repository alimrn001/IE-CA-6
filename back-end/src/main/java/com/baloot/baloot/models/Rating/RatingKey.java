package com.baloot.baloot.models.Rating;

import java.io.Serializable;
import java.util.Objects;

public class RatingKey implements Serializable {

    private String user;
    private int commodity;

    public RatingKey() {
    }

    public RatingKey(String user, int commodity) {
        this.user = user;
        this.commodity = commodity;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getCommodity() {
        return commodity;
    }

    public void setCommodity(int commodity) {
        this.commodity = commodity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RatingKey)) return false;
        RatingKey that = (RatingKey) o;
        return getUser() == that.getUser() &&
                getCommodity() == that.getCommodity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getCommodity());
    }
}