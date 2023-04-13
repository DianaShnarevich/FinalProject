package com.example.fitness.util.converters.audit;

import com.example.fitness.core.dto.audit.AuditDTO;
import com.example.fitness.core.dto.users.UserDTO;
import com.example.fitness.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class AuditDTOToAuditEntity implements Converter<AuditDTO, AuditEntity> {
    @Override
    public AuditEntity convert(AuditDTO source) {
        AuditEntity entity = new AuditEntity();
        entity.setUuid(source.getUuid());
        entity.setDtCreate(source.getDtCreate());
        UserDTO user = source.getUser();
        entity.setUserUuid(user.getUuid());
        entity.setFio(user.getFio());
        entity.setMail(user.getMail());
        entity.setRole(user.getRole());
        entity.setText(source.getText());
        entity.setId(source.getId());
        entity.setType(source.getType());
        return entity;
    }

}
