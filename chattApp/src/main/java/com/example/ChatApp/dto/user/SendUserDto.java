package com.example.ChatApp.dto.user;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SendUserDto {
	@NotBlank
	private int userId;
	@NotBlank
	private String username;
	
	public SendUserDto(int userId,String username) {
		this.userId = userId;
		this.username = username;
	}


}
