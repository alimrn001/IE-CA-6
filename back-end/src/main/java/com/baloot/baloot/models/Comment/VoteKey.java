package com.baloot.baloot.models.Comment;

import java.io.Serializable;
import java.util.Objects;

public class VoteKey implements Serializable {

    private String user;
    private int comment;

    public VoteKey() {
    }

    public VoteKey(String user, int comment) {
        this.user = user;
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VoteKey)) return false;
        VoteKey that = (VoteKey) o;
        return getUser() == that.getUser() &&
                getComment() == that.getComment();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getComment());
    }
}
