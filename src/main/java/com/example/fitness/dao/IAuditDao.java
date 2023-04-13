package com.example.fitness.dao;

import com.example.fitness.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAuditDao extends JpaRepository<AuditEntity, UUID> {

}
