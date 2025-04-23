package com.example.ChatApp.exception.handler;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.ChatApp.exception.AuthenticationFailedException;
import com.example.ChatApp.exception.ChatIdNotFoundException;
import com.example.ChatApp.exception.ChatPreserveFailedException;
import com.example.ChatApp.exception.ChatSaveFailedException;
import com.example.ChatApp.exception.UserAlreadyExistsException;
import com.example.ChatApp.exception.UserIdNotFoundException;
import com.example.ChatApp.exception.UserIdPreserveFailedException;
import com.example.ChatApp.exception.UserNameFoundException;
import com.example.ChatApp.exception.UserSaveFailedException;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleAuthenticationFailed_returns401() {
        AuthenticationFailedException ex = new AuthenticationFailedException("認証失敗");
        ResponseEntity<String> response = handler.handleAuthenticationFailed(ex);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("認証失敗", response.getBody());
    }

    @Test
    void handleIllegalArgument_returns400() {
        IllegalArgumentException ex = new IllegalArgumentException("不正な値");
        ResponseEntity<String> response = handler.handleIllegalArgument(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("不正な値", response.getBody());
    }

    @Test
    void handleUserSaveFailed_returns401() {
        UserSaveFailedException ex = new UserSaveFailedException("保存できませんでした");
        ResponseEntity<String> response = handler.handleUserSaveFailed(ex);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("保存できませんでした", response.getBody());
    }
    
    @Test
    void handleUserAlreadyExists_returns409() {
        UserAlreadyExistsException ex = new UserAlreadyExistsException("ユーザーはすでに存在します");
        ResponseEntity<String> response = handler.handleUserAlreadyExists(ex);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("ユーザーはすでに存在します", response.getBody());
    }

    @Test
    void handleChatSaveFailed_returns400() {
        ChatSaveFailedException ex = new ChatSaveFailedException("チャット保存失敗");
        ResponseEntity<String> response = handler.handleChatSaveFailed(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("チャット保存失敗", response.getBody());
    }

    @Test
    void handleUserNotFound_returns400() {
        UserIdNotFoundException ex = new UserIdNotFoundException("ユーザーが見つかりません");
        ResponseEntity<String> response = handler.handleUserNotFound(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("ユーザーが見つかりません", response.getBody());
    }

    @Test
    void handleUserNameFound_returns400() {
        UserNameFoundException ex = new UserNameFoundException("ユーザー名が見つかりません");
        ResponseEntity<String> response = handler.handleUserNameFound(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("ユーザー名が見つかりません", response.getBody());
    }

    @Test
    void handleUserIdPreserveFailed_returns404() {
        UserIdPreserveFailedException ex = new UserIdPreserveFailedException("ユーザーID取得失敗");
        ResponseEntity<String> response = handler.handleUserIdPreserveFailed(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("ユーザーID取得失敗", response.getBody());
    }

    @Test
    void handleChatPreserveFailed_returns404() {
        ChatPreserveFailedException ex = new ChatPreserveFailedException("チャット取得失敗");
        ResponseEntity<String> response = handler.handleChatPreserveFailed(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("チャット取得失敗", response.getBody());
    }

    @Test
    void handleChatIdNotFound_returns404() {
        ChatIdNotFoundException ex = new ChatIdNotFoundException("チャットIDが見つかりません");
        ResponseEntity<String> response = handler.handleChatIdNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("チャットIDが見つかりません", response.getBody());
    }

}
