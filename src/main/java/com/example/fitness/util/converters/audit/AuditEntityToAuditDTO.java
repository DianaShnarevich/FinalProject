package com.example.fitness.util.converters.audit;


import com.example.fitness.core.dto.audit.AuditDTO;
import com.example.fitness.core.dto.users.UserDTO;
import com.example.fitness.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditEntityToAuditDTO implements Converter<AuditEntity, AuditDTO> {
    @Override
    public AuditDTO convert(AuditEntity auditEntity) {
        return new AuditDTO(auditEntity.getUuid(),
                auditEntity.getDtCreate(),
                new UserDTO(auditEntity.getUuid(),
                        auditEntity.getMail(),
                        auditEntity.getFio(),
                        auditEntity.getUserRole()),
                auditEntity.getText(),
                auditEntity.getType(),
                auditEntity.getUuidService());
    }
}