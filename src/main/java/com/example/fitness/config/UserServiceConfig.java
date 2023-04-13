package com.example.fitness.config;

import com.example.fitness.dao.UserDao;
import com.example.fitness.service.UserService;
import com.example.fitness.service.api.IEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserServiceConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserService userService(UserDao userDao, IEmailService emailService, PasswordEncoder encoder) {
		return new UserService(userDao, emailService, encoder);
	}
}
