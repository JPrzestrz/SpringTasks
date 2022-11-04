package eti.isa.task1.engines.repository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import eti.isa.task1.engines.entity.Producer;
import eti.isa.task1.repository.Repository;
import eti.isa.task1.datastore.DataStore;

@org.springframework.stereotype.Repository
public class ProducerRepository implements Repository<Producer, String> {
    
    private final DataStore storage;

    /**
     * @param storage data store
     */
    @Autowired
    public ProducerRepository(DataStore storage) {
        this.storage = storage;
    }

    @Override
    public Optional<Producer> find(String name) {
        return storage.findProducer(name);
    }

    @Override
    public List<Producer> findAll() {
        return storage.findAllProducers();
    }

    @Override
    public void create(Producer entity) {
        storage.createProducer(entity);
    }

    @Override
    public void delete(Producer entity) {
        storage.deleteProducer(entity.getName());
    }

    @Override
    public void update(Producer entity) {
        storage.updateProducer(entity);
    }

}
