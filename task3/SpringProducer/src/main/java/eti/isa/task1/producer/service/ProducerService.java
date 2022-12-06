package eti.isa.task1.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eti.isa.task1.producer.repository.ProducerRepository;
import eti.isa.task1.producer.entity.Producer;
import eti.isa.task1.producer.event.ProducerEventRepository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
public class ProducerService {
    
    private final ProducerRepository repository;
    private ProducerEventRepository eventRepository;

    @Autowired
    public ProducerService(ProducerRepository repository, ProducerEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Producer> find(String name) {
        return repository.findById(name);
    }

    public List<Producer> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Producer create(Producer producer) {
        eventRepository.create(producer);
        return repository.save(producer);
    }

    @Transactional
    public void delete(Producer producer) {
        repository.delete(producer);
    }

    @Transactional
    public void update(Producer producer) {
        repository.save(producer);
    }

}
