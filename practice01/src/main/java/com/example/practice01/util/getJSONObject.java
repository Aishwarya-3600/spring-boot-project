package com.example.practice01.util;

import com.example.practice01.docs.StudentDocument;
import com.example.practice01.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class getJSONObject {

    public Student getStudentObject(Student s) {
        Student student = new Student();
        student.setId(s.getId());
        student.setFirstName(s.getFirstName());
        student.setLastName(s.getLastName());
        student.setMiddleName(s.getMiddleName());
        student.setAddresses(s.getAddresses());
        student.setGender(s.getGender());
        student.setMobileNumber(s.getMobileNumber());
        student.setStatus(s.getStatus());
        student.setCreatedDate(s.getCreatedDate());
        student.setDob(s.getDob());
        student.setEmail(s.getEmail());
        student.setDepartment(s.getDepartment());
        return student;
    }
    public StudentDocument getESStudentObject(Student s) {
        StudentDocument student = new StudentDocument();
        student.setId(s.getId());
        student.setFirstName(s.getFirstName());
        student.setLastName(s.getLastName());
        student.setMiddleName(s.getMiddleName());
        student.setGender(s.getGender());
        student.setMobileNumber(s.getMobileNumber());
        student.setStatus(s.getStatus());
        student.setDob(s.getDob());
        student.setEmail(s.getEmail());
       student.setDepartmentName(s.getDepartment().getName());
        return student;
    }
    public StudentDocument getESStudentObject(StudentDocument sd) {
        StudentDocument student = new StudentDocument();
        student.setId(sd.getId());
        student.setFirstName(sd.getFirstName());
        student.setLastName(sd.getLastName());
        student.setMiddleName(sd.getMiddleName());
        student.setGender(sd.getGender());
        student.setMobileNumber(sd.getMobileNumber());
        student.setStatus(sd.getStatus());
        student.setDob(sd.getDob());
        student.setEmail(sd.getEmail());
        student.setDepartmentName(sd.getDepartmentName());
        return student;
    }
}
