package eti.isa.task1.engines.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import eti.isa.task1.engines.repository.EngineRepository;
import eti.isa.task1.engines.entity.Engine;

@Service
public class EngineService {
    private EngineRepository repository;

    // ##
    // @param repository repository for engine entity
    @Autowired
    public EngineService(EngineRepository repository){
        this.repository = repository;
    }

    // Finds specific engine
    // @param name engine's name 
    public Optional<Engine> find(String name) {
        return repository.findByName(name);
    }

    // @return all engines
    public List<Engine> findAll() {
        return repository.findAll();
    }

    // creates new engine
    // @param engine new engine

    @Transactional
    public Engine create(Engine engine) {
        return repository.save(engine);
    }

    // updates existing engine
    // @param engine to be updated
    @Transactional
    public void update(Engine engine) {
        repository.save(engine);
    }

    // deletes existing engine
    // @param engine existing engine to be deleted
    @Transactional
    public void delete(String engine) {
        repository.deleteById(engine);
    }

}
