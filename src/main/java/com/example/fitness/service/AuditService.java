package com.example.fitness.service;

import com.example.fitness.core.dto.audit.AuditDTO;
import com.example.fitness.dao.IAuditDao;
import com.example.fitness.entity.AuditEntity;
import com.example.fitness.service.api.IAuditService;
import com.example.fitness.util.converters.audit.AuditDTOToAuditEntity;
import com.example.fitness.util.converters.audit.AuditEntityToAuditDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public class AuditService implements IAuditService {
    private final IAuditDao dao;
    private final AuditEntityToAuditDTO converterToDTO;
    private final AuditDTOToAuditEntity converterToEntity;
    private final ObjectMapper objectMapper;

    public AuditService(IAuditDao dao, AuditEntityToAuditDTO converterToDTO, AuditDTOToAuditEntity converterToEntity, ObjectMapper objectMapper) {
        this.dao = dao;
        this.converterToDTO = converterToDTO;
        this.converterToEntity = converterToEntity;
        this.objectMapper = new ObjectMapper();

    }

    @Override
    public Page<AuditEntity> getPage(Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        return dao.findAll(pageRequest);
    }

    @Override
    public AuditDTO get(UUID uuid) {
        return converterToDTO.convert(dao.findById(uuid)
                .orElseThrow(() -> new RuntimeException("not found")));
    }

}
