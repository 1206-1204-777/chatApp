package com.example.ChatApp.service.user;

import org.springframework.stereotype.Service;

import com.example.ChatApp.dto.user.SendUserDto;
import com.example.ChatApp.dto.user.UserLoginDto;
import com.example.ChatApp.dto.user.UserRegistationDto;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public SendUserDto loginUser(UserLoginDto dto) {
		
		return null;
	}

	@Override
	public boolean statusChangeuser(String userId) {
		
		return false;
	}

	@Override
	public SendUserDto registerUser(UserRegistationDto dto) {
		
		return null;
	}

}
