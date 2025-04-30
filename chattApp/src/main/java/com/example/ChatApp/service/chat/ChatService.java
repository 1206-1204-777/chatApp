package com.example.ChatApp.service.chat;

import jakarta.validation.Valid;

import com.example.ChatApp.dto.chat.ChatDataDto;
import com.example.ChatApp.dto.chat.ChatResponseDto;
import com.example.ChatApp.dto.chat.SendChatDto;

public interface ChatService {

	public  SendChatDto sendChat(ChatResponseDto dto);
	
	public ChatResponseDto reception(@Valid ChatDataDto dto);
}
