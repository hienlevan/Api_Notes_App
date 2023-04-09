package com.app.controllers;

import com.app.dtos.LoginRequest;
import com.app.models.User;
import com.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        userService.saveUser(user);
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Kiểm tra thông tin đăng nhập của người dùng
        if (userService.authenticateUser(username, password)) {
            return ResponseEntity.ok("Đăng nhập thành công");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Tên đăng nhập hoặc mật khẩu không chính xác");
        }
    }


    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @DeleteMapping(value = "{userId}")
    public String deleteUser(@PathVariable(value = "userId") int userId){

        User user = userService.getUserById(userId);
        if(user == null){
            System.out.println("Người dùng không tồn tại");
            return "Người dùng không tồn tại";
        }
        System.out.println("Người dùng tồn tại");
        return userService.deleteUser(user);
    }

}
