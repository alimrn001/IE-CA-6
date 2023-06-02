package com.baloot.baloot.models.Provider;

import com.baloot.baloot.models.Comment.Comment;
import com.baloot.baloot.models.Commodity.Commodity;
import jakarta.persistence.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

@Entity
public class Provider {
    @Id
    private int id;

    private String name;

    private LocalDate registryDate;

    private double avgCommoditiesRate;

    private String image;

    @OneToMany //(mappedBy = "provider") //not sure ????!!!?!
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Commodity> commoditiesProvided;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegistryDate(LocalDate registryDate) {
        this.registryDate = registryDate;
    }

    public void setAvgCommoditiesRate(double avgCommoditiesRate) {
        this.avgCommoditiesRate = avgCommoditiesRate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCommoditiesProvided(Set<Commodity> commoditiesProvided) {
        this.commoditiesProvided = commoditiesProvided;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getRegistryDate() {
        return registryDate;
    }

    public double getAvgCommoditiesRate() {
        return avgCommoditiesRate;
    }

    public String getImage() {
        return image;
    }

    public Set<Commodity> getCommoditiesProvided() {
        return commoditiesProvided;
    }

}
