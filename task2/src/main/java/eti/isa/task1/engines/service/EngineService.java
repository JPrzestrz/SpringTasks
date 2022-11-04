package eti.isa.task1.engines.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return repository.find(name);
    }

    // @return all engines
    public List<Engine> findAll() {
        return repository.findAll();
    }

    // creates new engine
    // @param engine new engine
    public void create(Engine engine) {
        repository.create(engine);
    }

    // updates existing engine
    // @param engine to be updated
    public void update(Engine engine) {
        repository.update(engine);
    }

    // deletes existing engine
    // @param engine existing engine to be deleted
    public void delete(String engine) {
        repository.delete(repository.find(engine).orElseThrow());
    }

}
