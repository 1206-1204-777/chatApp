package com.example.ChatApp.service.user;

import com.example.ChatApp.dto.user.SendUserDto;
import com.example.ChatApp.dto.user.UserLoginDto;
import com.example.ChatApp.dto.user.UserRegistationDto;

public interface UserService {
	public SendUserDto loginUser(UserLoginDto dto);

	public boolean statusChangeuser(String userId);
	
	public SendUserDto registerUser(UserRegistationDto dto);
}
