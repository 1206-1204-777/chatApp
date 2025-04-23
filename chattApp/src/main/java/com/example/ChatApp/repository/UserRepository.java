package com.example.ChatApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ChatApp.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String>{
	Optional<UserEntity>findByUsername(String username);
}
