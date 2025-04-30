package com.example.ChatApp.controller.chat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.ChatApp.dto.chat.ChatDataDto;
import com.example.ChatApp.dto.chat.ChatResponseDto;
import com.example.ChatApp.exception.UserNameFoundException;
import com.example.ChatApp.service.chat.ChatService;

@ExtendWith(MockitoExtension.class)//Mockの初期化
class ChatControllerTest {
	
	@Mock
	private ChatService service;
	@InjectMocks
	private ChatController controller;
	

	@SuppressWarnings("null")
	@Test
	void 送信成功時() {
		ChatDataDto dto = new ChatDataDto();
		dto.setUserId(1L);
		dto.setUsername("taro");
		dto.setChatMessage("hello");
		
		ChatResponseDto reslut = new ChatResponseDto(1L,"taro","hello");
		when(service.reception(dto)).thenReturn(reslut);
		
		/*戻り値を取得*/
		ChatResponseDto response = controller.sendChat(dto).getBody();

		
		assertEquals("taro",response.getUsername());
		assertEquals("hello",response.getChatMessage());
	}
	
	@Test
	void 送信失敗時() {
		ChatDataDto dto = new ChatDataDto();
		dto.setUsername(null);
		dto.setChatMessage(null);
		
		assertThrows(UserNameFoundException.class,()->{
			controller.sendChat(dto);
		});
	}

}
