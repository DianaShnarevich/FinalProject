package com.example.fitness.util.converters.user;

import com.example.fitness.core.dto.users.UserDTO;
import com.example.fitness.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;

public class UserEntityToUserDTO implements Converter<UserEntity, UserDTO> {
	@Override
	public UserDTO convert(UserEntity source) {
		return new UserDTO(source);
	}
}
