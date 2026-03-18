package com.example.demo_redis.service;

import com.example.demo_redis.entity.UserEntity;
import com.example.demo_redis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "users", key = "#id")
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @CachePut(value = "users", key = "#user.id")
    public UserEntity saveUser(UserEntity user) {
        return userRepository.save(user);
    }

    @CacheEvict(value = "users", key = "#id")
    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return !userRepository.findById(id).isPresent();
    }

    @Cacheable(value = "users", key = "'all'")
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @CachePut(value = "users", key = "#id")
    public UserEntity updateUser(Long id, UserEntity userEntityDetails) {
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            userEntity.setName(userEntityDetails.getName());
            userEntity.setEmail(userEntityDetails.getEmail());
            return userRepository.save(userEntity);
        }
        return null;
    }
}
