package com.example.fitness.service.api;

import com.example.fitness.core.dto.PageDTO;
import com.example.fitness.core.dto.audit.AuditDTO;
import com.example.fitness.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAuditService <T> {
    Page<AuditEntity> getPage(Integer page, Integer pageSize);
    AuditDTO get(UUID uuid);
}
