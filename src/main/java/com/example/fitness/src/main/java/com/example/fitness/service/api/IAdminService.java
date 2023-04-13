package com.example.fitness.service.api;

import com.example.fitness.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IAdminService {
	Page<UserEntity> getPage(Integer page, Integer pageSize);
	UserEntity get(UUID uuid);
	void add(UserEntity entity);
	void delete(UUID uuid, LocalDateTime dtUpdate);
	void update(UserEntity newUser, UUID uuid, LocalDateTime dtUpdate);
}
