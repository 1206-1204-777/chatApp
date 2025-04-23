package com.example.ChatApp.exception;

public class ChatIdNotFoundException extends RuntimeException {
	public ChatIdNotFoundException(String message) {
		super(message);
	}
}
