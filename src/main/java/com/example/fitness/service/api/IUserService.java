package com.example.fitness.service.api;

import com.example.fitness.core.dto.users.UserLoginDTO;
import com.example.fitness.entity.UserEntity;

public interface IUserService {
	void register(UserEntity entity);
	void verify(String code, String mail);
	UserEntity login(UserLoginDTO dto);
	UserEntity getCard(String mail);
	UserEntity getUserByMail(String mail);
}
