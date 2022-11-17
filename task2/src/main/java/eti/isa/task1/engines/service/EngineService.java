package eti.isa.task1.engines.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import eti.isa.task1.engines.repository.EngineRepository;
import eti.isa.task1.engines.repository.ProducerRepository;
import eti.isa.task1.engines.entity.Engine;
import eti.isa.task1.engines.entity.Producer;

@Service
public class EngineService {
    private EngineRepository repository;
    private ProducerRepository producerRepository;

    // ##
    // @param repository repository for engine entity
    @Autowired
    public EngineService(EngineRepository repository, ProducerRepository producerRepository){
        this.repository = repository;
        this.producerRepository = producerRepository;
    }

    // Finds specific engine
    // @param name engine's name 
    public Optional<Engine> find(String name) {
        return repository.findByName(name);
    }

    public Optional<Engine> find(String name, Long id) {
        Optional<Producer> producer = producerRepository.findById(name);
        if(producer.isPresent()) {
            return repository.findByIdAndProducer(id,producer.get());
        } else {
            return Optional.empty();
        }
    }

    public Optional<Engine> findById(Long id) {
        return repository.findById(id);
    }

    // @return all engines
    public List<Engine> findAll() {
        return repository.findAll();
    }

    public List<Engine> findAll(Producer producer) {
        return repository.findAllByProducer(producer);
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
    public void delete(Long engine) {
        repository.deleteById(engine);
    }

}
