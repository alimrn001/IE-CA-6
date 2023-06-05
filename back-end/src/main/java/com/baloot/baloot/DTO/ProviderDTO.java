package com.baloot.baloot.DTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProviderDTO {

    private int id;

    private String name;

    private LocalDate registryDate;

    private int commoditiesNum;

    private double avgCommoditiesRate;

    private ArrayList<Integer> commoditiesProvided;

    private String image;


    public ProviderDTO(int id, String name, LocalDate registryDate, int commoditiesNum, double avgCommoditiesRate, String image) {
        this.id = id;
        this.name = name;
        this.registryDate = registryDate;
        this.commoditiesNum = commoditiesNum;
        this.avgCommoditiesRate = avgCommoditiesRate;
        this.commoditiesProvided = new ArrayList<>();
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegistryDate(LocalDate registryDate) {
        this.registryDate = registryDate;
    }

    public void setCommoditiesNum(int commoditiesNum) {
        this.commoditiesNum = commoditiesNum;
    }

    public void setAvgCommoditiesRate(double avgCommoditiesRate) {
        this.avgCommoditiesRate = avgCommoditiesRate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCommoditiesProvided(ArrayList<Integer> commoditiesProvided) {
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

    public int getCommoditiesNum() {
        return commoditiesNum;
    }

    public double getAvgCommoditiesRate() {
        return avgCommoditiesRate;
    }

    public String getImage() {
        return image;
    }

    public ArrayList<Integer> getCommoditiesProvided() {
        return commoditiesProvided;
    }

}
