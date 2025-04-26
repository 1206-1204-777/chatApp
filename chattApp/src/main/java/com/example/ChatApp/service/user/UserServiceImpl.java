package com.example.ChatApp.service.user;

import java.util.Optional;
import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ChatApp.dto.user.SendUserDto;
import com.example.ChatApp.dto.user.UserLoginDto;
import com.example.ChatApp.dto.user.UserRegistrationDto;
import com.example.ChatApp.entity.UserEntity;
import com.example.ChatApp.exception.AuthenticationFailedException;
import com.example.ChatApp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;
	private final PasswordEncoder encoder;

	public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
		this.encoder = encoder;
		this.repository = repository;
	}

	@Override
	public SendUserDto loginUser(UserLoginDto dto) {
		String username = dto.getUsername();
		String password = dto.getPassword();

		if (username.isBlank() || password.isBlank()) {
			throw new AuthenticationFailedException("ユーザー名またはパスワードが入力されていません。");
		}
		UserEntity user = repository.findByUsername(username)
				.orElseThrow(() -> new AuthenticationFailedException("ユーザーが存在しません。"));

		if (!encoder.matches(password, user.getPassword())) {
			throw new AuthenticationFailedException("パスワードが一致しません");
		}
		/*ログイン状態に変更*/
		user.setStatus(true);
		repository.save(user);
		return new SendUserDto(user.getUserId(),user.getUsername());
		
	}

	@Override
	public boolean statusChangeuser(Long userId) {
		Optional<UserEntity> optionalUser = repository.findById(userId);
		
		if(!optionalUser.isPresent()) {
			throw new AuthenticationFailedException("ユーザーが存在しません。");
		}
		UserEntity user = optionalUser.get();
		user.setStatus(false);
		repository.save(user);
		

		return true;
	}

	@Override
	public SendUserDto registerUser(UserRegistrationDto dto) {
		Random rand = new Random();
		String username = dto.getUsername();
		String password = dto.getPassword();
		UserEntity user = new UserEntity();

		if (username.isBlank() || password.isBlank()) {
			throw new AuthenticationFailedException("ユーザー名またはパスワードが入力されていません。");
		}
		user.setUserId(rand.nextInt(1,101));
		user.setUsername(username);
		user.setPassword(encoder.encode(password));
		user.setStatus(true);
		repository.save(user);
		return new SendUserDto(user.getUserId(),user.getUsername());
	}

}
