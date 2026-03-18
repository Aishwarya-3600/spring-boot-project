//package com.example.activmq01.service;
//
//import com.example.activmq01.dto.UserDto;
//import com.example.activmq01.dto.UserProfileDto;
//import com.example.activmq01.entity.User;
//import com.example.activmq01.entity.UserProfile;
//import com.example.activmq01.repository.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Slf4j
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private JmsTemplate jmsTemplate; // Assuming you have a JmsTemplate configured for sending messages
//
////    public User createUser(UserDto userDto) {
////        User user = new User();
////        user.setName(userDto.getName());
////        user.setEmail(userDto.getEmail());
////        if (userDto.getProfile() != null) {
////            UserProfileDto profileDto = userDto.getProfile();
////            UserProfile profile = new UserProfile();
////            profile.setAddress(profileDto.getAddress());
////            profile.setPhone(profileDto.getPhone());
////            user.setProfile(profile);
////        }
////        log.info("sending message to activemq: {}", user);
////        jmsTemplate.convertAndSend("user-queue", userDto.toString()); // Send user data to ActiveMQ
////         // Assuming you have a JMS template configured for sending messages
////        return userRepository.save(user);
////    }
//
//    @JmsListener(destination = "user-queue")
//    private void recieveUser(String user) {
//        //User tempUser = // Assuming you have a method to convert string back to User object
//        log.info("recieved the user {} from queue", user);
//        // This method can be used to process the user data received from the queue
//        System.out.println("Received user from queue: " + user.toString());
//        // You can add additional processing logic here if needed
//    }
//
//    public Optional<User> getUser(Long id) {
//        return userRepository.findById(id);
//    }
//
//    public User updateUser(Long id, UserDto userDto) {
//        Optional<User> optionalUser = userRepository.findById(id);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.setName(userDto.getName());
//            user.setEmail(userDto.getEmail());
//            if (userDto.getProfile() != null) {
//                UserProfileDto profileDto = userDto.getProfile();
//                UserProfile profile = user.getProfile();
//                if (profile == null) {
//                    profile = new UserProfile();
//                }
//                profile.setAddress(profileDto.getAddress());
//                profile.setPhone(profileDto.getPhone());
//                user.setProfile(profile);
//            }
//            return userRepository.save(user);
//        }
//        return null;
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
//}
//
