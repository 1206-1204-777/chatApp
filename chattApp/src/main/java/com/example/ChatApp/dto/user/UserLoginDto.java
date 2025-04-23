package com.example.ChatApp.dto.user;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserLoginDto {
	@NotBlank(message = "ユーザー名は必須です")
	private String username;
	@NotBlank(message = "パスワードは必須です")
	private String password;
}
