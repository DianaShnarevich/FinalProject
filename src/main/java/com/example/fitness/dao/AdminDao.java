package com.example.fitness.dao;

import com.example.fitness.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminDao extends JpaRepository<UserEntity, UUID> {
	Optional<UserEntity> findByMail(String mail);
}
