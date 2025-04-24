package com.example.ChatApp.dto.user;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SendUserDto {
	@NotBlank
	private String userId;
	@NotBlank
	private String username;
	
	public SendUserDto(String userId,String username) {
		this.userId = userId;
		this.username = username;
	}


}
