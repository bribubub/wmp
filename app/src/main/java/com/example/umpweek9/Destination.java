package com.example.umpweek9;

public class Destination {
    private String name;
    private String description;
    private int imageResId; // Resource ID for the image

    public Destination(String name, String description, int imageResId) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
