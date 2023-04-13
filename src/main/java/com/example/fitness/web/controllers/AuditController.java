package com.example.fitness.web.controllers;

import com.example.fitness.core.dto.PageDTO;
import com.example.fitness.core.dto.audit.AuditDTO;
import com.example.fitness.core.dto.users.UserDTO;
import com.example.fitness.entity.AuditEntity;
import com.example.fitness.entity.UserEntity;
import com.example.fitness.service.api.IAuditService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {
    private final IAuditService auditService;
    private final ConversionService conversionService;


    public AuditController(IAuditService auditService, ConversionService conversionService) {
        this.auditService = auditService;
        this.conversionService = conversionService;
    }

    @RequestMapping(path = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<AuditDTO> get(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.status(HttpStatus.OK).body(auditService.get(uuid));
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<?> create(@RequestBody @Valid AuditDTO auditDTO) {
//        auditService.create(new AuditDTO(auditDTO.getUser(), auditDTO.getText(), auditDTO.getType(), auditDTO.getUuidService()));
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTO<AuditDTO>> get(
                    @RequestParam(name = "page", required = false, defaultValue = "0")
                    Integer page,
                    @RequestParam(name = "size", required = false, defaultValue = "20")
                    Integer size){
        Page<AuditEntity> audit = auditService.getPage(page, size);
        Set<AuditDTO> auditSet = new HashSet<>();
        for(AuditEntity entity : audit.getContent()) {
            AuditDTO dto = conversionService.convert(entity, AuditDTO.class);
            auditSet.add(dto);
        }
        PageDTO.PageBuilder<AuditDTO> builder = PageDTO.builder();
        PageDTO<AuditDTO> pageOfAudit = builder.setNumber(audit.getNumber())
                .setSize(audit.getSize())
                .setTotal_pages(audit.getTotalPages())
                .setTotal_elements(audit.getTotalElements())
                .setFirst(audit.isFirst())
                .setLast(audit.isLast())
                .setNumber_of_elements(audit.getNumberOfElements())
                .setContent(auditSet).build();
        return ResponseEntity.status(HttpStatus.OK).body(pageOfAudit);
    }}
