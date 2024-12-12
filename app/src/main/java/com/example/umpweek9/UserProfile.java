package com.example.umpweek9;

public class UserProfile {
    private String profileImage;

    // Default constructor (required by Firestore)
    public UserProfile() {
    }

    // Constructor
    public UserProfile(String profileImage) {
        this.profileImage = profileImage;
    }

    // Getter and Setter
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}