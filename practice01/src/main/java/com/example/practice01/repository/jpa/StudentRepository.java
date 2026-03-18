package com.example.practice01.repository.jpa;

import com.example.practice01.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long>  {
    List<Student> findByDepartmentId(Long departmentId);
}

