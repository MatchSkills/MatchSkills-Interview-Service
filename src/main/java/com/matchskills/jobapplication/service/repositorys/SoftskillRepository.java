package com.matchskills.jobapplication.service.repositorys;

import com.matchskills.jobapplication.service.entitys.Softskill;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SoftskillRepository extends MongoRepository<Softskill, String> {
    Optional<Softskill> findByName(String name);
}