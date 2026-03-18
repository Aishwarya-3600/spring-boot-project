package com.example.demo_redis.controller;

import com.example.demo_redis.entity.UserEntity;
import com.example.demo_redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<UserEntity>> createUser(@RequestBody UserEntity userEntity) {
        UserEntity createdUserEntity = userService.saveUser(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new ApiResponse<>(HttpStatus.CREATED.value(), createdUserEntity, "User created successfully")
        );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserEntity>> getUserById(@PathVariable Long id) {
        UserEntity user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(
                new ApiResponse<>(HttpStatus.OK.value(), user, "User found")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse<>(HttpStatus.NOT_FOUND.value(), null, "User not found")
            );
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok(
                new ApiResponse<>(HttpStatus.OK.value(), null, "User deleted successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse<>(HttpStatus.NOT_FOUND.value(), null, "User not found")
            );
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserEntity>>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(
            new ApiResponse<>(HttpStatus.OK.value(), users, "Users fetched successfully")
        );
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserEntity>> updateUser(@PathVariable Long id, @RequestBody UserEntity userEntity) {
        UserEntity updatedUser = userService.updateUser(id, userEntity);
        if (updatedUser != null) {
            return ResponseEntity.ok(
                new ApiResponse<>(HttpStatus.OK.value(), updatedUser, "User updated successfully")
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiResponse<>(HttpStatus.NOT_FOUND.value(), null, "User not found")
            );
        }
    }
}
