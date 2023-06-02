package com.baloot.baloot.models.Category;

import com.baloot.baloot.models.Commodity.Commodity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Set;
@Entity
public class Category {
    @Id
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private Set<Commodity> commodities;

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCommodities(Set<Commodity> commodities) {
        this.commodities = commodities;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Set<Commodity> getCommodities() {
        return commodities;
    }

}
