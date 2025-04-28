package com.example.ChatApp.controller.user;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ChatApp.dto.user.SendUserDto;
import com.example.ChatApp.dto.user.UserLoginDto;
import com.example.ChatApp.dto.user.UserRegistrationDto;
import com.example.ChatApp.exception.UserSaveFailedException;
import com.example.ChatApp.service.user.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private UserService service;

	public UserController(UserService service) {
		this.service = service;
	}
	/*ログイン用*/
	@PostMapping("/login")
	public ResponseEntity<SendUserDto>login(@RequestBody @Valid UserLoginDto dto){//@RequestBodyはユーザーからの情報をdtoに格納する
		if(dto.getUsername() == null|| dto.getPassword() == null) {
			throw new IllegalArgumentException("ユーザー名またはパスワードが入力されていません。");
		}
		SendUserDto result =service.loginUser(dto);
		
		return ResponseEntity.ok(result);

		
	}
	
	/*ログアウト処理*/
	@PostMapping("/logout")
	public ResponseEntity<Boolean> logout(@RequestBody @Valid SendUserDto dto){
		boolean reslut = service.statusChangeuser(dto.getUserId());
		return ResponseEntity.ok(reslut);
		
	}
	/*登録用*/
	@PostMapping("/register")
	public ResponseEntity<SendUserDto> Registration(@RequestBody @Valid UserRegistrationDto dto){
		if(dto.getUsername()  == null || dto.getPassword() == null) {
			throw new UserSaveFailedException("ユーザー登録ができませんでした。");
		}
		SendUserDto result = service.registerUser(dto);
		return ResponseEntity.ok(result);
	}
	
}
