package com.baloot.baloot.models.Commodity;
import com.baloot.baloot.models.Category.Category;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Set;

@Entity
public class Commodity {
    @Id
    private int id;

    private String name;

    private int providerId;

    private int price;

    @ManyToMany()
    @JoinTable(name = "commodity_has_category", joinColumns = @JoinColumn(name = "commodity_id"),inverseJoinColumns = @JoinColumn(name = "category_name"))
    private Set<Category> categories;

    private double rating;

    private int inStock;

    private String image;

    private int numOfRatings;

//    private ArrayList<Integer> comments = new ArrayList<>();
}
