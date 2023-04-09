package com.app.services;

import com.app.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public User saveUser(User user);

    public List<User> getAllUser();

    public String deleteUser(User user);

    public User getUserById(int userId);

    public User getUserByUsername(String username);

    public boolean authenticateUser(String username, String password);
}
