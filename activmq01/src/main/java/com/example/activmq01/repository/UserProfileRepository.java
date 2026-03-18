package com.example.activmq01.repository;

import com.example.activmq01.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}

