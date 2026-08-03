package com.matchskills.interview.service.repositorys;

import com.matchskills.interview.service.entitys.SoftskillEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SoftskillRepository extends MongoRepository<SoftskillEntity, String> {
    Optional<SoftskillEntity> findByName(String name);
}