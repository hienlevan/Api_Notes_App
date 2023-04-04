package com.notesapp.controller;


import com.notesapp.entities.User;
import com.notesapp.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Read Specific User from Details
    @GetMapping("{userId}")
    public User getUserDetails(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    // Read All User Details from DB
    @GetMapping()
    public List<User> getAllUserDetails(){
        return userService.getAllUsers();
    }

    @PostMapping
    public String createUserDetails(@RequestBody User user){
        userService.createUser(user);
        return "User Created Successfully";
    }

    @PutMapping
    public String updateUserDetails(@RequestBody User user){
        userService.updateUser(user);
        return "User Updated Successfully";
    }

    @DeleteMapping("{userId}")
    public String deleteUserDetails(@PathVariable("userId") String userId){
        userService.deleteUser(userId);
        return "User Deleted Successfully";
    }
}
