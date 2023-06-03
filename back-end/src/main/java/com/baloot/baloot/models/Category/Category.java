//package com.baloot.baloot.models.Category;
//
//import com.baloot.baloot.models.Commodity.Commodity;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import jakarta.persistence.*;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.Set;
//@Entity
//public class Category {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "category_name")
//    private String categoryName;
//
//    @ManyToMany(mappedBy = "categories")
//    private Set<Commodity> commodities = new HashSet<>();
//
//
//    public Category() {}
//
//    public Category(String categoryName) {
//        this.categoryName = categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
//
//    public void setCommodities(Set<Commodity> commodities) {
//        this.commodities = commodities;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public Set<Commodity> getCommodities() {
//        return commodities;
//    }
//
//}
