package com.example.practice01.docs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Getter
@Setter
@Document(indexName = "students")
public class StudentDocument {
    @Id
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dob;
    private String gender;
    private String departmentName;
    private String email;
    private String mobileNumber;
    private String status;


}

