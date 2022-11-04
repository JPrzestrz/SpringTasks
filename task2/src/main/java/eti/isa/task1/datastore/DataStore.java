package eti.isa.task1.datastore;

import org.springframework.stereotype.Component;

import eti.isa.task1.engines.entity.Engine;
import eti.isa.task1.engines.entity.Producer;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class DataStore {

    // Set of all available producers
    private Set<Producer> producers = new HashSet<>();

    // Set of all available engines
    private Set<Engine> engines = new HashSet<>();

    // Seeks for all producers
    public synchronized List<Producer> findAllProducers() {
        return new ArrayList<>(producers);
    }

    // Seeks for all engines
    public synchronized List<Engine> findAllEngines() {
        return new ArrayList<>(engines);
    }

    // Find producer with given name
    public synchronized Optional<Producer> findProducer(String name) {
        return producers.stream()
                .filter(producer -> producer.getName().equals(name))
                .findFirst();
    }

    // Find engine with given name
    public synchronized Optional<Engine> findEngine(String name) {
        return engines.stream()
                .filter(engine -> engine.getName().equals(name))
                .findFirst();
    }

    // Create producer 
    public synchronized void createProducer(Producer producer) throws IllegalArgumentException {
        findProducer(producer.getName()).ifPresentOrElse(
            original -> {
                throw new IllegalArgumentException(
                    String.format("The brand name \"%s\" is not unique", producer.getName()));
                }, () -> producers.add(producer));
    }

    // Create engine 
    public synchronized void createEngine(Engine engine) throws IllegalArgumentException {
        findEngine(engine.getName()).ifPresentOrElse(
            original -> {
                throw new IllegalArgumentException(
                    String.format("The engine name\"%s\" is not unique", engine.getName()));
            }, () -> engines.add(engine));
    }

    // Delete producer with given name
    public synchronized void deleteProducer(String name) throws IllegalArgumentException {
        findProducer(name).ifPresentOrElse(
            producers::remove,
            () -> {
                throw new IllegalArgumentException(
                    String.format("The producer with name \"%s\" doesnt exist", name));
            });
    }

    // Delete engine with given name
    public synchronized void deleteEngine(String name) throws IllegalArgumentException {
        findEngine(name).ifPresentOrElse(
            engines::remove,
            () ->{
                throw new IllegalArgumentException(
                    String.format("The engine with name \"%s\"doesnt exist",name)
                );
            });
    }

    // Update producer
    public synchronized void updateProducer(Producer producer) throws IllegalArgumentException {
        findProducer(producer.getName()).ifPresentOrElse(
            original -> {
                producers.remove(original);
                producers.add(producer);
            }, 
            () -> {
                throw new IllegalArgumentException(
                    String.format("The producer with name \"%s\"doesnt exist", producer.getName())
                );
            });
    }

    // Update engine _
    public synchronized void updateEngine(Engine engine) throws IllegalArgumentException {
        findEngine(engine.getName()).ifPresentOrElse(
            original -> {
                engines.remove(original);
                engines.add(engine);
            }, 
            () -> {
                throw new IllegalArgumentException(
                    String.format("The engine with name \"%s\"doesnt exist", engine.getName())
                );
            });
    }

}
