package com.example.fitness.config;

import com.example.fitness.dao.AdminDao;
import com.example.fitness.service.AdminService;
import com.example.fitness.service.api.IAdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminServiceConfig {

	@Bean
	public IAdminService adminService(AdminDao adminDao, PasswordEncoder encoder){
		return new AdminService(adminDao, encoder);
	}
}
