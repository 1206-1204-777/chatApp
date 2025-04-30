package com.example.ChatApp.dto.user;

import jakarta.validation.constraints.Positive;

import lombok.Data;

@Data
public class SendUserDto {
	@Positive
	private Long userId;

	private String username;
	public SendUserDto(Long userId,String username) {
		this.userId = userId;
		this.username = username;

	}


}
	