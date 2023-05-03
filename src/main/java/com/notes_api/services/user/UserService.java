package com.notes_api.services.user;

import com.notes_api.dtos.UserDto;
import com.notes_api.enntities.User;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public interface UserService {

    User getUserByid(int id);

    List<User> getAllUsers();

    User createUser(UserDto userDto);

    User updateUser(int id, UserDto userDto);

    User deleteUser(int id);


}
