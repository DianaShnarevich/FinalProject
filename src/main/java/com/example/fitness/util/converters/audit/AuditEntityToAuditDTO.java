package com.example.fitness.util.converters.audit;


import com.example.fitness.core.dto.audit.AuditDTO;
import com.example.fitness.core.dto.users.UserDTO;
import com.example.fitness.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditEntityToAuditDTO implements Converter<AuditEntity, AuditDTO> {
    @Override
    public AuditDTO convert(AuditEntity source) {
        AuditDTO auditDTO = new AuditDTO();
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(source.getUserUuid());
        userDTO.setFio(source.getFio());
        userDTO.setMail(source.getMail());
        userDTO.setRole(source.getRole());

        auditDTO.setUuid(source.getUuid());
        auditDTO.setDtCreate(source.getDtCreate());
        auditDTO.setUser(userDTO);
        auditDTO.setText(source.getText());
        auditDTO.setType(source.getType());
        auditDTO.setId(source.getId());
        return auditDTO;
    }
}