package com.app.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.dtos.UserDto;
import com.app.entities.User;
import com.app.repositories.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public String createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = new User(userDto.getUsername(),
				this.passwordEncoder.encode(userDto.getPassword()));
		
		userRepository.save(user);
		return user.getUsername();
	}
}
