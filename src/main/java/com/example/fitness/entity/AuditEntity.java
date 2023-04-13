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
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Column(name = "uuid_user")
    private UUID uuidUser;
    @Column(name = "mail")
    private String mail;
    @Column(name = "fio")
    private String fio;
    @Enumerated(EnumType.STRING)
    @Column(name = "entity")
    private UserRole role;
    @Column(name = "text")
    private String text;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EntityType type;
    @Column(name = "uuid_service")
    private String uuidService;

    public AuditEntity(UUID uuid, LocalDateTime dtCreate, UUID uuidUser, String mail, String fio, UserRole role, String text, EntityType type, String uuidService) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.uuidUser = uuidUser;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.text = text;
        this.type = type;
        this.uuidService = uuidService;
    }


    public AuditEntity() {

    }

    public AuditEntity(LocalDateTime dtCreate, UUID uuid, String mail, String fio, UserRole role, String text, EntityType type, String uuidService) {
    }


    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public UUID getUuidUser() {
        return uuidUser;
    }

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public UserRole getUserRole() {
        return role;
    }

    public String getText() {
        return text;
    }

    public EntityType getType() {
        return type;
    }

    public String getUuidService() {
        return uuidService;
    }
}
