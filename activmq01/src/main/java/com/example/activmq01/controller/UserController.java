//package com.example.activmq01.controller;
//
//import com.example.activmq01.dto.UserDto;
//import com.example.activmq01.entity.User;
//import com.example.activmq01.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping
//    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
//        User user = userService.createUser(userDto);
//        return ResponseEntity.ok(user);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUser(@PathVariable Long id) {
//        Optional<User> user = userService.getUser(id);
//        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
//        User updated = userService.updateUser(id, userDto);
//        if (updated != null) {
//            return ResponseEntity.ok(updated);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }
//}
//
