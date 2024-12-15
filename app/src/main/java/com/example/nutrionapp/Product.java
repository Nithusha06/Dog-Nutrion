package com.example.nutrionapp;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private double rating;
    private String imageUri;

    public Product(int id, String name, String description, double price, double rating, String imageUri) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.imageUri = imageUri;
    }

    public Product(String name, String description, double price, double rating, String imageUri) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.imageUri = imageUri;
    }

    // Getters and setters for each field
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
