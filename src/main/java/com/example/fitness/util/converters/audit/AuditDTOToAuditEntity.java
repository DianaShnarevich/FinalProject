package com.example.fitness.util.converters.audit;

import com.example.fitness.core.dto.audit.AuditDTO;
import com.example.fitness.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class AuditDTOToAuditEntity implements Converter<AuditDTO, AuditEntity> {
    @Override
    public AuditEntity convert(AuditDTO auditDTO) {
        return new AuditEntity(
                auditDTO.getDtCreate(),
                auditDTO.getUser().getUuid(),
                auditDTO.getUser().getMail(),
                auditDTO.getUser().getFio(),
                auditDTO.getUser().getUserRole(),
                auditDTO.getText(),
                auditDTO.getType(),
                auditDTO.getUuidService());
    }

}
