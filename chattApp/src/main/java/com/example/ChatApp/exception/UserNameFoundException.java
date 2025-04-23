package com.example.ChatApp.exception;

public class UserNameFoundException extends RuntimeException {
	public UserNameFoundException(String message) {
		super(message);
	}
}
