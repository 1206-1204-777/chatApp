package com.example.ChatApp.controller.chat.room;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChatApp.service.chat.room.ChatRoomService;

@RestController
@RequestMapping("/api/chat/room")
public class ChatRoomController {
	private ChatRoomService service;
	
	public ChatRoomController(ChatRoomService service) {
		this.service = service;
	}
}
