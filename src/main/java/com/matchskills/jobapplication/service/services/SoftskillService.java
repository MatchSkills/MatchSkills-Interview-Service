package com.matchskills.jobapplication.service.services;

import com.matchskills.jobapplication.service.entitys.Softskill;
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

    public Softskill create(Softskill softskill) {
        return repository.save(softskill);
    }

    public List<Softskill> getAll() {
        return repository.findAll();
    }

    public Softskill getByName(String name) {
        return repository.findByName(name)
                .orElseThrow(SoftskillNotFoundException::new);
    }
}
