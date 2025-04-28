package com.example.ChatApp.dto.chat;

import lombok.Data;

@Data
public class ChatResponseDto {
	private Long userId;
	private String username;
	private String chatMessage;
	
	public ChatResponseDto(Long userId,String username,String chatMessage){
		this.userId = userId;
		this.username = username;
		this.chatMessage = chatMessage;
	}
}
