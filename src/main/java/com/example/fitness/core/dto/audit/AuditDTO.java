package com.example.fitness.core.dto.audit;


import com.example.fitness.core.dto.users.UserDTO;
import com.example.fitness.core.enums.EntityType;
import com.example.fitness.util.serializers.LocalDateTimeToMillisCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    private UserDTO user;
    private String text;
    private EntityType type;
    private String id;

    public AuditDTO() {
    }

    public AuditDTO(UUID uuid, LocalDateTime dtCreate, UserDTO user, String text, EntityType type, String id) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.user = user;
        this.text = text;
        this.type = type;
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EntityType getType() {
        return type;
    }

    public void setType(EntityType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
