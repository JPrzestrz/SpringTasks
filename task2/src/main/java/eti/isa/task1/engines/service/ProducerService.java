package eti.isa.task1.engines.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eti.isa.task1.engines.repository.ProducerRepository;
import eti.isa.task1.engines.entity.Producer;

import java.util.List;
import java.util.Optional;

@Service
public class ProducerService {
    
    private final ProducerRepository repository;

    @Autowired
    public ProducerService(ProducerRepository repository) {
        this.repository = repository;
    }

    public Optional<Producer> find(String name) {
        return repository.find(name);
    }

    public List<Producer> findAll() {
        return repository.findAll();
    }

    public void create(Producer producer) {
        repository.create(producer);
    }

    public void delete(Producer producer) {
        repository.delete(producer);
    }

}
