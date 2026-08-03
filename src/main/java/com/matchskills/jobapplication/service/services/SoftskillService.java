package com.matchskills.jobapplication.service.services;

import com.matchskills.jobapplication.service.entitys.SoftskillEntity;
import com.matchskills.jobapplication.service.exceptions.customs.softskills.SoftskillNotFoundException;
import com.matchskills.jobapplication.service.repositorys.SoftskillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftskillService {

    final private SoftskillRepository repository;

    public SoftskillService(SoftskillRepository repository) {
        this.repository = repository;
    }

    public SoftskillEntity create(SoftskillEntity softskillEntity) {
        return repository.save(softskillEntity);
    }

    public List<SoftskillEntity> getAll() {
        return repository.findAll();
    }

    public SoftskillEntity getByName(String name) {
        return repository.findByName(name)
                .orElseThrow(SoftskillNotFoundException::new);
    }
}
