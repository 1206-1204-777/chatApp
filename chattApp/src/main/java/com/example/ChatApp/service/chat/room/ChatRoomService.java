package com.example.ChatApp.service.chat.room;

import org.springframework.http.ResponseEntity;

import com.example.ChatApp.dto.chat.room.ChatRoomDto;
import com.example.ChatApp.entity.ChatRoomEntity;

public interface ChatRoomService {
	public ResponseEntity<ChatRoomEntity> chatRoomIn(ChatRoomDto dto);

	public ResponseEntity<ChatRoomEntity> chatRoomOut(ChatRoomDto dto);
}
