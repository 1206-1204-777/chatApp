package com.example.ChatApp.exception;

public class UserSaveFailedException extends RuntimeException {
	public UserSaveFailedException(String message) {
		super(message);
	}
}
