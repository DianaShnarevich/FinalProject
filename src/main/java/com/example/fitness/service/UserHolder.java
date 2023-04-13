package com.example.fitness.service;

import com.example.fitness.core.dto.users.UserTokenDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {

	public UserTokenDTO getUser(){
		return (UserTokenDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
