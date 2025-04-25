package com.example.ChatApp.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserRegistrationDto {
@NotBlank
private String username;
@NotBlank
private String password;
@Min(1)
private int role = 1;
}
