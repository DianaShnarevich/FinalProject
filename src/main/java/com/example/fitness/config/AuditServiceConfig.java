package com.example.fitness.config;

import com.example.fitness.dao.IAuditDao;
import com.example.fitness.service.AuditService;
import com.example.fitness.service.api.IAuditService;
import com.example.fitness.util.converters.audit.AuditDTOToAuditEntity;
import com.example.fitness.util.converters.audit.AuditEntityToAuditDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class AuditServiceConfig {
    @Bean
    public IAuditService auditService(IAuditDao dao, AuditEntityToAuditDTO converterToDTO, AuditDTOToAuditEntity converterToEntity, ObjectMapper objectMapper){
        return new AuditService(dao, converterToDTO, converterToEntity, objectMapper);
    }
}
