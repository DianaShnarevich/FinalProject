package com.example.fitness.web.controllers;

import com.example.fitness.core.dto.PageDTO;
import com.example.fitness.core.dto.audit.AuditDTO;
import com.example.fitness.service.api.IAuditService;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {
    private final IAuditService iAuditService;


    public AuditController(IAuditService iAuditService) {
        this.iAuditService = iAuditService;
    }

    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<AuditDTO> get(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(iAuditService.get(uuid));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid AuditDTO auditDTO) {
        iAuditService.create(new AuditDTO(auditDTO.getUser(), auditDTO.getText(), auditDTO.getType(), auditDTO.getUuidService()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTO> get(@RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(defaultValue = "20") @Min(0) int size) {
        return ResponseEntity.status(HttpStatus.OK).body(iAuditService.get(page, size));
    }}
