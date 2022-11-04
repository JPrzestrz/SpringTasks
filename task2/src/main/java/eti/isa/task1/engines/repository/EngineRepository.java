package eti.isa.task1.engines.repository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import eti.isa.task1.repository.Repository;
import eti.isa.task1.engines.entity.Engine;
import eti.isa.task1.datastore.DataStore;

@org.springframework.stereotype.Repository
public class EngineRepository implements Repository<Engine, String>{
    
    private final DataStore storage;

    @Autowired
    public EngineRepository(DataStore storage){
        this.storage = storage;
    }

    @Override
    public Optional<Engine> find(String name) {
        return storage.findEngine(name);
    }

    @Override
    public List<Engine> findAll() {
        return storage.findAllEngines();
    }

    @Override
    public void create(Engine entity) {
        storage.createEngine(entity);
    }

    @Override
    public void update(Engine entity){
        storage.updateEngine(entity);
    }

    @Override
    public void delete(Engine entity) {
        storage.deleteEngine(entity.getName());
    }
}
