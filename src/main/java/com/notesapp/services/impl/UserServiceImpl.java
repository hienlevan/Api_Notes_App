package com.notesapp.services.impl;

import com.notesapp.entities.User;
import com.notesapp.repositories.UserRepository;
import com.notesapp.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public String createUser(User user) {
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String updateUser(User user) {
        userRepository.save(user);
        return "Success";
    }

    @Override
    public String deleteUser(String userId) {
        userRepository.deleteById(userId);
        return "Success";
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
