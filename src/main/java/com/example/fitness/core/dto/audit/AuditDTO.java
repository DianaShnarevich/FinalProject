package com.example.fitness.core.dto.audit;


import com.example.fitness.core.dto.users.UserDTO;
import com.example.fitness.core.enums.EntityType;
import com.example.fitness.util.serializers.LocalDateTimeToMillisCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class AuditDTO {
    private UUID uuid;
    @JsonSerialize(using = LocalDateTimeToMillisCustomSerializer.class)
    private LocalDateTime dtCreate;
    @NotNull
    private UserDTO user;
    @NotBlank
    private String text;

    private EntityType type;
    @NotBlank
    private String uuidService;
    public AuditDTO() {
    }

    public AuditDTO(LocalDateTime dtCreate, UserDTO user, String text, EntityType type, String uuidService) {
        this.dtCreate = dtCreate;
        this.user = user;
        this.text = text;
        this.type = type;
        this.uuidService = uuidService;
    }

    public AuditDTO(UUID uuid, LocalDateTime dtCreate, UserDTO user, String text, EntityType type, String uuidService) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.user = user;
        this.text = text;
        this.type = type;
        this.uuidService = uuidService;
    }

    public AuditDTO(UserDTO user, String text, EntityType type, String uuidService) {
    }


    public AuditDTO(UUID uuid, LocalDateTime dtCreate, UserDTO user, String text, jakarta.persistence.metamodel.EntityType type, String uuidService) {
    }
}
