package com.example.ChatApp.controller.chat;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChatApp.dto.chat.ChatDataDto;
import com.example.ChatApp.dto.chat.ChatResponseDto;
import com.example.ChatApp.exception.UserNameFoundException;
import com.example.ChatApp.service.chat.ChatService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
	private ChatService service;

	public ChatController(ChatService service) {
		this.service = service;
	}
	@PostMapping("/chat")
	public ResponseEntity<ChatResponseDto> sendChat(@RequestBody @Valid ChatDataDto dto){
		
		if(dto.getUsername() == null || dto.getChatMessage() == null) {
			throw new UserNameFoundException("このユーザー名は登録されていません。");
		}
		ChatResponseDto reslut = service.reception(dto);
		return ResponseEntity.ok(reslut);
	}
}
