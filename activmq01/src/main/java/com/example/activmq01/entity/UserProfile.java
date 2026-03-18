package com.example.activmq01.entity;

import jakarta.persistence.*;

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String phone;

    @OneToOne(mappedBy = "profile")
    private User user;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}

