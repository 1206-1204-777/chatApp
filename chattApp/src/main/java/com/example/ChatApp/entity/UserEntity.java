package com.example.ChatApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Column(name = "password",nullable = false)
	private String password;

	@Column(name = "status")
	private boolean status;
}
