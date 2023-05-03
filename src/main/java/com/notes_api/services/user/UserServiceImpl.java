package com.notes_api.services.user;


import com.notes_api.dtos.UserDto;
import com.notes_api.enntities.User;
import com.notes_api.exceptions.InvalidException;
import com.notes_api.exceptions.NotFoundException;
import com.notes_api.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getUserByid(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User có id %s không tồn tại", id)));
    }

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        if(ObjectUtils.isEmpty(userDto.getEmail())){
            throw new InvalidException("Vui lòng điền Email!");
        }
        if(ObjectUtils.isEmpty(userDto.getPassword())){
            throw new InvalidException("Vui lòng điền Password!");
        }

        user.setEmail(userDto.getEmail().trim());
        user.setPassword(userDto.getPassword().trim());
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(int id, UserDto userDto) {
        User user = getUserByid(id);
        if(ObjectUtils.isEmpty(userDto.getEmail())){
            throw new InvalidException("Vui lòng điền Email!");
        }
        if(ObjectUtils.isEmpty(userDto.getPassword())){
            throw new InvalidException("Vui lòng điền Password!");
        }

        user.setEmail(userDto.getEmail().trim());
        user.setPassword(userDto.getPassword().trim());
        userRepository.save(user);
        return user;
    }

    @Override
    public User deleteUser(int id) {
        User user = getUserByid(id);
        userRepository.delete(user);
        return user;
    }
}

