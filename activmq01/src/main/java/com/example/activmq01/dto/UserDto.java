package com.example.activmq01.dto;

public class UserDto {
    private String name;
    private String email;
    private UserProfileDto profile;
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public UserProfileDto getProfile() { return profile; }
    public void setProfile(UserProfileDto profile) { this.profile = profile; }
}

