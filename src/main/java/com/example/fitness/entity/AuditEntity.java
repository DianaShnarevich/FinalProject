package com.example.fitness.entity;

import com.example.fitness.core.enums.EntityType;
import com.example.fitness.core.enums.UserRole;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "fitness", name = "audit")
public class AuditEntity {
    @Id
    @GeneratedValue
    private UUID uuid;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;
    @Column(name = "uuid_user")
    private UUID userUuid;
    private String mail;
    private String fio;
    @Enumerated(value = EnumType.STRING)
    private UserRole role;
    private String text;

    @Enumerated(value = EnumType.STRING)
    private EntityType type;
    private String id;

    public AuditEntity() {
    }

    public AuditEntity(UUID uuid, LocalDateTime dtCreate, UUID userUuid, String mail, String fio, UserRole role, String text, EntityType type, String id) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.userUuid = userUuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
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

    public UUID getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(UUID userUuid) {
        this.userUuid = userUuid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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
