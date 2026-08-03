package com.matchskills.jobapplication.service.repositorys;

import com.matchskills.jobapplication.service.entitys.SoftskillEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SoftskillRepository extends MongoRepository<SoftskillEntity, String> {
    Optional<SoftskillEntity> findByName(String name);
}