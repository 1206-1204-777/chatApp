package com.example.ChatApp.entity;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "chat_room")
public class ChatRoomEntity {
@Id
@Column(name = "id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(name = "user_name",nullable = false,unique = true)
private String userName;
@Column(name = "roomname")
private String roomname;
@Column(name = "updated_at",updatable = true)
private Timestamp updatedAt;
}
