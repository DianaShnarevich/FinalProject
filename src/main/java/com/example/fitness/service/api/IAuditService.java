package com.example.fitness.service.api;

import com.example.fitness.core.dto.PageDTO;
import com.example.fitness.core.dto.audit.AuditDTO;

import java.util.UUID;

public interface IAuditService <T> {
    void create(AuditDTO auditDTO);

    PageDTO<T> get(int page, int size);

    AuditDTO get(UUID id);
}
