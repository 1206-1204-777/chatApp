package com.example.ChatApp.service.chat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.ChatApp.dto.chat.ChatDataDto;
import com.example.ChatApp.dto.chat.ChatResponseDto;
import com.example.ChatApp.dto.chat.SendChatDto;
import com.example.ChatApp.entity.ChatEntity;
import com.example.ChatApp.entity.UserEntity;
import com.example.ChatApp.exception.ChatIdNotFoundException;
import com.example.ChatApp.repository.ChatRepository;
import com.example.ChatApp.repository.UserRepository;

@Service
public class ChatServiceImpl implements ChatService {

	private UserRepository userRepository;
	private ChatRepository chatRepository;

	public ChatServiceImpl(UserRepository userRepository, ChatRepository chatRepository) {
		this.userRepository = userRepository;
		this.chatRepository = chatRepository;
	}

	@Override
	public SendChatDto sendChat(ChatResponseDto dto) {
		dto.getChatMessage();
		dto.getUserId();

		return null;
	}

	@Transactional
	/*チャット内容を受け取り保存する*/
	@Override
	public ChatResponseDto reception(ChatDataDto dto) {

		UserEntity user = userRepository.findByUsername(dto.getUsername())
				.orElseThrow(() -> new ChatIdNotFoundException("ユーザーが存在しません。"));

		ChatEntity entity = new ChatEntity();
		entity.setUsername(dto.getUsername());
		entity.setChatMessage(dto.getChatMessage());
		entity.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));//送信時間を格納	

		chatRepository.save(entity);
		System.out.println("chatMessage : " + entity.getChatMessage());
		return new ChatResponseDto(user.getId(), user.getUsername(), dto.getChatMessage());
	}

}
