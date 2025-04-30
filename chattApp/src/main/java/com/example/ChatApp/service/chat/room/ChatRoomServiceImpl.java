package com.example.ChatApp.service.chat.room;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ChatApp.dto.chat.room.ChatRoomDto;
import com.example.ChatApp.entity.ChatRoomEntity;

@Service
public class ChatRoomServiceImpl implements ChatRoomService{

	@Override
	public ResponseEntity<ChatRoomEntity> chatRoomIn(ChatRoomDto dto) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public ResponseEntity<ChatRoomEntity> chatRoomOut(ChatRoomDto dto) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
