package com.baloot.baloot.models.Provider;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Provider {
    @Id
    private int id;

    private String name;

    private LocalDate registryDate;

    private double avgCommoditiesRate;

    private String image;

    //private ArrayList<Integer> commoditiesProvided;

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

}
