package com.example.umpweek9;

public class Destination {
    private final String name;
    private final String description;
    private final int imageResId;

    public Destination(String name, String description, int imageResId) {
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
    }

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
