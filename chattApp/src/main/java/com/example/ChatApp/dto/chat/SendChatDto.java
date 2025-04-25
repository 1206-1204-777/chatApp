package com.example.ChatApp.dto.chat;

import lombok.Data;

@Data
public class SendChatDto {
private int userId;
private String username;
private String chatMessage;
}
