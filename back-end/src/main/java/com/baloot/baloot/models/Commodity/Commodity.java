package com.baloot.baloot.models.Commodity;

import com.baloot.baloot.models.Category.Category;
import com.baloot.baloot.models.Comment.Comment;
import com.baloot.baloot.models.Provider.Provider;
import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Commodity {
    @Id
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PROVIDER_ID")
    private Provider provider;

    private int price;

    @ManyToMany
    @JoinTable(name = "commodity_has_category", joinColumns = @JoinColumn(name = "commodity_id"),inverseJoinColumns = @JoinColumn(name = "category_name"))
    private Set<Category> categories;

    private double rating;

    private int inStock;

    private String image;

    private int numOfRatings;

    @OneToMany //(mappedBy = "commodity") //not sure ????!!!?!
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Comment> comments;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public void addNewRating(int newRating) {
        this.rating = (((this.rating*this.numOfRatings) + newRating)/(this.numOfRatings+1));
        this.numOfRatings ++;
    }

    public void updateUserRating(int previousRating, int newRating) {
        this.rating += ((double)(newRating - previousRating)/numOfRatings);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Provider getProvider() {
        return provider;
    }

    public int getPrice() {
        return price;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public double getRating() {
        return rating;
    }

    public int getInStock() {
        return inStock;
    }

    public String getImage() {
        return image;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public boolean hasCategory(Category category) {
        return categories.contains(category);
    }

}
