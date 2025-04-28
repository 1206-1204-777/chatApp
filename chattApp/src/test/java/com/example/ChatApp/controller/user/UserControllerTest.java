package com.example.ChatApp.controller.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.ChatApp.dto.user.SendUserDto;
import com.example.ChatApp.dto.user.UserLoginDto;
import com.example.ChatApp.dto.user.UserRegistrationDto;
import com.example.ChatApp.exception.UserSaveFailedException;
import com.example.ChatApp.service.user.UserService;

class UserControllerTest2 {
	 @Mock
	    private UserService service;

	    @InjectMocks
	    private UserController controller;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

		@Test
		void ログイン失敗時_空のユーザー名とパスワード() {
			UserLoginDto loginDto = new UserLoginDto();
			loginDto.setUsername("");
			loginDto.setPassword("");
		
			when(service.loginUser(any(UserLoginDto.class))).thenThrow(new IllegalArgumentException("ユーザー名またはパスワードが入力されていません。"));
		
			assertThrows(IllegalArgumentException.class, () -> {
				controller.login(loginDto);
			});
		}

	@Test
	void 登録失敗時_空のユーザー名() {
		UserRegistrationDto registration = new UserRegistrationDto();
		registration.setUsername("");
		registration.setPassword("validPassword123");

		when(service.registerUser(registration)).thenThrow(new UserSaveFailedException("ユーザー名が空です。"));

		assertThrows(UserSaveFailedException.class, () -> {
			controller.Registration(registration);
		});
	}

	@Test
	void 登録失敗時_空のパスワード() {
		UserRegistrationDto registration = new UserRegistrationDto();
		registration.setUsername("valid_user");
		registration.setPassword("");

		when(service.registerUser(registration)).thenThrow(new UserSaveFailedException("パスワードが空です。"));

		assertThrows(UserSaveFailedException.class, () -> {
			controller.Registration(registration);
		});
	}

	@SuppressWarnings("null")
	@Test
	void ログイン成功時_正しいユーザー名とパスワード() {
		UserLoginDto loginDto = new UserLoginDto();
		loginDto.setUsername("valid_user");
		loginDto.setPassword("validPassword123");

		SendUserDto registration = new SendUserDto(3L, "valid_user");
		when(service.loginUser(loginDto)).thenReturn(registration);

		ResponseEntity<SendUserDto> response = controller.login(loginDto);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(3L, response.getBody().getUserId());
		assertEquals("valid_user", response.getBody().getUsername());
	}

	@SuppressWarnings("null")
	@Test
	void 登録成功時_新しいユーザー() {
		UserRegistrationDto registration = new UserRegistrationDto();
		registration.setUsername("new_user");
		registration.setPassword("newPassword123");

		SendUserDto result = new SendUserDto(4L, "new_user");
		when(service.registerUser(registration)).thenReturn(result);

		ResponseEntity<SendUserDto> response = controller.Registration(registration);
		assertEquals(200, response.getStatusCode().value());
		assertEquals("new_user", response.getBody().getUsername());
		assertEquals(4L, response.getBody().getUserId());
	}
	
		@SuppressWarnings("null")
		@Test
		void ログイン成功時() {
			/*ユーザー情報送信*/
			UserLoginDto loginDto = new UserLoginDto();
			loginDto.setUsername("taro");
			loginDto.setPassword("pass123");

			/*ユーザー情報取得*/
			SendUserDto registration = new SendUserDto(1L,"taro");
			/*Mockへの登録と仮の戻り値を設定*/
			when(service.loginUser(loginDto)).thenReturn(registration);

			ResponseEntity<SendUserDto> response = controller.login(loginDto);
			assertEquals(200, response.getStatusCode().value());
			assertEquals(1L, response.getBody().getUserId());
			assertEquals("taro", response.getBody().getUsername());

		}
		
		@Test
		void ログイン失敗＿ユーザー入力なし() {
			UserLoginDto loginDto = new UserLoginDto();
			loginDto.setUsername(null);
			loginDto.setPassword("pass123");

			assertThrows(IllegalArgumentException.class,()->{
				controller.login(loginDto);
			});
		}
		
		@Test
		void ログイン失敗＿パスワード入力なし() {
			UserLoginDto loginDto = new UserLoginDto();
			loginDto.setUsername("taro");
			loginDto.setPassword(null);

			assertThrows(IllegalArgumentException.class,()->{
				controller.login(loginDto);
			});
		}

		@Test
		void ログイン失敗時_イレギュラー() {
			UserLoginDto loginDto = new UserLoginDto();
			loginDto.setUsername("tar");
			loginDto.setPassword("pas123");
			
			when(service.loginUser(loginDto)).thenThrow(new IllegalArgumentException(
					"ログインに失敗しました。"));
			
			assertThrows(IllegalArgumentException.class,()->
			{controller.login(loginDto);});
		}
		
		@SuppressWarnings("null")
		@Test
		void 登録成功時() {
			/*ユーザー情報入力*/
			UserRegistrationDto registration = new UserRegistrationDto();
			registration.setUsername("taro");
			registration.setPassword("pass123");
			
			SendUserDto result = new SendUserDto(1L,"taro");
			
			when(service.registerUser(registration)).thenReturn(result);
			
			ResponseEntity<SendUserDto> response = controller.Registration(registration);
			assertEquals(200,response.getStatusCode().value());
			assertEquals("taro",response.getBody().getUsername());
			assertEquals(1L,response.getBody().getUserId());
			
		}
		
		
		@Test
		void 登録失敗時() {
			
			UserRegistrationDto registration = new UserRegistrationDto();
			registration.setUsername(null);
			registration.setPassword(null);

			
			assertThrows(UserSaveFailedException.class,()->
			{controller.Registration(registration);});
		}
		
		


}
