package com.example.nutrionapp;

public class ProductItem {
    private String name;
    private String brand;
    private String type;
    private String ageGroup;
    private String price;
    private int imageResource;

    // Constructor
    public ProductItem(String name, String brand, String type, String ageGroup, String price, int imageResource) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.ageGroup = ageGroup;
        this.price = price;
        this.imageResource = imageResource;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
