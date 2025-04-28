package com.example.ChatApp.dto.chat;

import lombok.Data;

@Data
public class SendChatDto {
private Long userId;
private String username;
private String chatMessage;
}
