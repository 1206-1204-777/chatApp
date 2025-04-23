package com.example.ChatApp.exception;

public class UserIdNotFoundException extends RuntimeException {
	public UserIdNotFoundException(String message) {
		super(message);
	}
}
