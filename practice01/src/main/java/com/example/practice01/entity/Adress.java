package com.example.practice01.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Adress {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String doorNo;
    @NonNull
    private String street;
    @NonNull
    private String city;
    @NonNull
    private String pinCode;
    @NonNull
    private String state;
    @NonNull
    private String country;
    private String landmark;
    private String lat;
    private String lng;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stud_id")
    @JsonBackReference
    private Student student;
}
