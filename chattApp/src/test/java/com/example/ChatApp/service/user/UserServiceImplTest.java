package com.example.ChatApp.service.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.ChatApp.dto.user.SendUserDto;
import com.example.ChatApp.dto.user.UserLoginDto;
import com.example.ChatApp.dto.user.UserRegistrationDto;
import com.example.ChatApp.entity.UserEntity;
import com.example.ChatApp.exception.AuthenticationFailedException;
import com.example.ChatApp.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void loginUser_正常系() {
        // Arrange
        UserLoginDto dto = new UserLoginDto();
        dto.setUsername("taro");
        dto.setPassword("pass123");

        UserEntity user = new UserEntity();
        user.setId("u001");
        user.setUsername("taro");
        user.setPassword("encoded123");

        when(repository.findByUsername("taro")).thenReturn(Optional.of(user));
        when(encoder.matches("pass123", "encoded123")).thenReturn(true);

        // Act
        SendUserDto result = userService.loginUser(dto);

        // Assert
        assertEquals("u001", result.getUserId());
        assertEquals("taro", result.getUsername());
    }

    @Test
    void loginUser_ユーザーなし() {
        UserLoginDto dto = new UserLoginDto();
        dto.setUsername("unknown");
        dto.setPassword("pass123");

        when(repository.findByUsername("unknown")).thenReturn(Optional.empty());

        assertThrows(AuthenticationFailedException.class, () -> {
            userService.loginUser(dto);
        });
    }

    @Test
    void loginUser_パスワード不一致() {
        UserLoginDto dto = new UserLoginDto();
        dto.setUsername("taro");
        dto.setPassword("wrongpass");

        UserEntity user = new UserEntity();
        user.setId("u001");
        user.setUsername("taro");
        user.setPassword("encoded123");

        when(repository.findByUsername("taro")).thenReturn(Optional.of(user));
        when(encoder.matches("wrongpass", "encoded123")).thenReturn(false);

        assertThrows(AuthenticationFailedException.class, () -> {
            userService.loginUser(dto);
        });
    }
    
    
    @Test
    void loginUser_ユーザー名空文字_例外スロー() {
        UserLoginDto dto = new UserLoginDto();
        dto.setUsername(""); // 空文字
        dto.setPassword("validpass");

        assertThrows(AuthenticationFailedException.class, () -> {
            userService.loginUser(dto);
        });
    }

    @Test
    void registerUser_パスワード空文字_例外スロー() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername("taro");
        dto.setPassword(""); // 空文字

        assertThrows(AuthenticationFailedException.class, () -> {
            userService.registerUser(dto);
        });
    }


    @Test
    void registerUser_正常系() {
        UserRegistrationDto dto = new UserRegistrationDto();
        dto.setUsername("newuser");
        dto.setPassword("pass123");

        when(encoder.encode("pass123")).thenReturn("encoded123");

        SendUserDto result = userService.registerUser(dto);

        assertEquals("newuser", result.getUsername());
        assertNotNull(result.getUserId()); // IDは実装で生成される想定
    }

    @Test
    void statusChangeUser_正常系() {
        UserEntity user = new UserEntity();
        user.setId("u001");
        user.setStatus(true);

        when(repository.findById("u001")).thenReturn(Optional.of(user));

        boolean result = userService.statusChangeuser("u001");

        assertTrue(result);
        assertFalse(user.isStatus()); // ステータスが false になったことを確認
    }

    @Test
    void statusChangeUser_ユーザーなし() {
        when(repository.findById("notFound")).thenReturn(Optional.empty());

        assertThrows(AuthenticationFailedException.class, () -> {
            userService.statusChangeuser("notFound");
        });
    }
}
