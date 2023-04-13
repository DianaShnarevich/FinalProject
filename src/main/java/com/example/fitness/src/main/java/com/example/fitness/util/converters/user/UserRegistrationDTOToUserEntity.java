package com.example.fitness.util.converters.user;

import com.example.fitness.core.dto.users.UserRegistrationDTO;
import com.example.fitness.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;

public class UserRegistrationDTOToUserEntity implements Converter<UserRegistrationDTO, UserEntity> {
	@Override
	public UserEntity convert(UserRegistrationDTO source) {
		return new UserEntity(source);
	}
}
