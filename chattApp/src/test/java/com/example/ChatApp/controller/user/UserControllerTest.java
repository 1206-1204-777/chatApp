package com.example.ChatApp.controller.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.ChatApp.dto.user.SendUserDto;
import com.example.ChatApp.dto.user.UserLoginDto;
import com.example.ChatApp.dto.user.UserRegistrationDto;
import com.example.ChatApp.exception.AuthenticationFailedException;
import com.example.ChatApp.service.user.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
	//使用したい機能を持つ仮クラスを作成
	@Mock
	private UserService service;
	//テスト対象を指定
	@InjectMocks
	private UserController controller;

	@Test
	void ログイン成功時() {
		/*ユーザー情報送信*/
		UserLoginDto loginDto = new UserLoginDto();
		loginDto.setUsername("taro");
		loginDto.setPassword("pass123");

		/*ユーザー情報取得*/
		SendUserDto registration = new SendUserDto("user1", "taro");
		/*Mockへの登録と仮の戻り値を設定*/
		when(service.loginUser(loginDto)).thenReturn(registration);

		ResponseEntity<SendUserDto> response = controller.login(loginDto);
		assertEquals(200, response.getStatusCode().value());
		assertEquals("user1", response.getBody().getUserId());
		assertEquals("taro", response.getBody().getUsername());

	}

	@Test
	void ログイン失敗時() {
		UserLoginDto loginDto = new UserLoginDto();
		loginDto.setUsername("tar");
		loginDto.setPassword("pas123");
		
		when(service.loginUser(loginDto)).thenThrow(new AuthenticationFailedException(
				"ログインに失敗しました。"));
		
		assertThrows(AuthenticationFailedException.class,()->
		{controller.login(loginDto);});
	}
	
	@Test
	void 登録成功時() {
		/*ユーザー情報入力*/
		UserRegistrationDto registration = new UserRegistrationDto();
		registration.setUsername("taro");
		registration.setPassword("pass123");
		
		SendUserDto result = new SendUserDto("user1","taro");
		
		when(service.registerUser(registration)).thenReturn(result);
		
		ResponseEntity<SendUserDto> response = controller.Registration(registration);
		assertEquals(200,response.getStatusCode().value());
		assertEquals("taro",response.getBody().getUsername());
		assertEquals("user1",response.getBody().getUserId());
		
	}
	
	
	@Test
	void 登録失敗時() {
		
		UserRegistrationDto registration = new UserRegistrationDto();
		registration.setUsername("");
		registration.setPassword("");
		
		//SendUserDto reslut = new SendUserDto(registration.getUsername(),registration.getPassword());
		
		when(service.registerUser(registration)).thenThrow(new IllegalArgumentException(
				"登録に失敗しました。"));
		
		assertThrows(IllegalArgumentException.class,()->
		{controller.Registration(registration);});
	}
	

}
