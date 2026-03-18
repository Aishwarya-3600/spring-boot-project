package com.example.practice01.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String firstName;
    private String middleName;
    @NonNull
    private String lastName;
    @NonNull
    private Date dob;
    @NonNull
    private String gender;
    @NonNull
    private String mobileNumber;
    @NonNull
    private String email;
    @NonNull
    private String status;
    @NonNull
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "id")
    private Department department;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Adress> addresses;

}
