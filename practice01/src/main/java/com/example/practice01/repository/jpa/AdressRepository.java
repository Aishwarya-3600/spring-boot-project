package com.example.practice01.repository.jpa;

import com.example.practice01.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Adress, Long> {
}

