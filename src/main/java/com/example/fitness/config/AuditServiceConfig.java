package com.example.fitness.config;

import com.example.fitness.dao.IAuditDao;
import com.example.fitness.service.AuditService;
import com.example.fitness.service.api.IAuditService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class AuditServiceConfig {
    @Bean
    public IAuditService auditService(IAuditDao dao, ConversionService conversionService){
        return new AuditService(dao, conversionService);
    }
}
