package com.example.fitness.util.converters.user;

import com.example.fitness.core.dto.users.UserCreateDTO;
import com.example.fitness.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;


public class UserCreateDTOToUserEntity implements Converter<UserCreateDTO, UserEntity> {
	@Override
	public UserEntity convert(UserCreateDTO source) {
		return new UserEntity(source);
	}
}
