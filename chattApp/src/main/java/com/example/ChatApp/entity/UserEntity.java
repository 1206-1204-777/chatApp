package com.example.ChatApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "user_id",nullable = false,unique = true)
	private Long userId;

	@Column(name = "username", nullable = false, unique = true, updatable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "status")
	private boolean status;
}
