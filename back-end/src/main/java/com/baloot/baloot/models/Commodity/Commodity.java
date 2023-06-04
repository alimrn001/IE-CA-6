package com.baloot.baloot.models.Commodity;

//import com.baloot.baloot.models.Category.Category;
import com.baloot.baloot.models.Comment.Comment;
import com.baloot.baloot.models.Provider.Provider;
import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Commodity {
    @Id
    private int id;

    private String name;

    @Column(insertable = false, updatable = false)
    private int providerId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "providerId")
    private Provider provider;

    private int price;

    @ElementCollection(targetClass = String.class)
    @LazyCollection(LazyCollectionOption.FALSE)
//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "categoryId")})
    private Set<String> categories;

    private double rating;

    private int inStock;

    private String image;

    private int numOfRatings;

    @OneToMany //(mappedBy = "commodity") //not sure ????!!!?!
    @JoinColumn(name = "commodity_id")
    private Set<Comment> comments = new HashSet<>();


    public Commodity(int id, String name, Provider provider, int price, double rating, int inStock, String image) {
        this.id = id;
        this.name = name;
        this.provider = provider;
        this.price = price;
        this.rating = rating;
        this.inStock = inStock;
        this.image = image;
        this.numOfRatings = 1;
    }

    public Commodity() {
        this.numOfRatings = 1;
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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategories(Set<String> categories) {
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

    public void setProvider(Provider provider) {
        this.provider = provider;
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

    public int getProviderId() {
        return providerId;
    }

    public Provider getProvider() {
        return provider;
    }

    public int getPrice() {
        return price;
    }

    public Set<String> getCategories() {
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

    public boolean hasCategory(String category) {
        return categories.contains(category);
    }

}
