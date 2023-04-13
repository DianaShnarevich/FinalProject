package com.example.fitness.util.converters.user;

import com.example.fitness.core.dto.users.UserTokenDTO;
import com.example.fitness.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;

public class UserEntityToUserToken implements Converter<UserEntity, UserTokenDTO> {

	@Override
	public UserTokenDTO convert(UserEntity source) {
		return new UserTokenDTO(source);
	}
}
