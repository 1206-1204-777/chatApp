package com.example.ChatApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ChatApp.entity.ChatEntity;
@Repository
public interface ChatRepository extends JpaRepository<ChatEntity,Long>{

}
