package com.example.ChatApp.exception;

public class ChatSaveFailedException extends RuntimeException {
	public ChatSaveFailedException(String message) {
		super(message);
	}
}