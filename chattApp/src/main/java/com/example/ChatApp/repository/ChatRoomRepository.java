package com.example.ChatApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ChatApp.entity.ChatRoomEntity;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity,Integer>{

}
