package com.example.ChatApp.service.chat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ChatApp.dto.chat.ChatDataDto;
import com.example.ChatApp.dto.chat.ChatResponseDto;
import com.example.ChatApp.entity.ChatEntity;
import com.example.ChatApp.entity.UserEntity;
import com.example.ChatApp.exception.ChatIdNotFoundException;
import com.example.ChatApp.repository.ChatRepository;
import com.example.ChatApp.repository.UserRepository;

/*チャット機能テスト*/
@ExtendWith(MockitoExtension.class)
class ChatServiceImplTest {

	@Mock
	private ChatRepository repository;
	
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private ChatServiceImpl service;
	
	@Test
	void チャット保存_成功時() {
		/*Mockユーザーの作成*/
		UserEntity user = new UserEntity();
		user.setId(1L);
		user.setUsername("taro");
		when(userRepository.findByUsername("taro")).thenReturn(Optional.of(user));
		

		ChatDataDto dto = new ChatDataDto();
		dto.setUserId(1L);
		dto.setUsername("taro");
		dto.setChatMessage("test");
		

		ChatEntity entity = new ChatEntity();
		entity.setId(dto.getUserId());
		entity.setUsername(dto.getUsername());
		entity.setChatMessage(dto.getChatMessage());
		entity.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
		
		when(repository.save(any(ChatEntity.class))).thenReturn(entity);
		
		ChatResponseDto result = service.reception(dto);
		assertEquals("test",result.getChatMessage());
	}
	
	@Test
	void チャット保存_ユーザー登録なし() {
		ChatDataDto dto = new ChatDataDto();
		dto.setUsername("taro");
		when(userRepository.findByUsername("taro")).thenReturn(Optional.empty());
		assertThrows(ChatIdNotFoundException.class,()
				-> {service.reception(dto);});
	}

}
