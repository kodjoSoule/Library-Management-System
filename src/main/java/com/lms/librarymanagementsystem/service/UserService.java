package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.User;
import com.lms.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
