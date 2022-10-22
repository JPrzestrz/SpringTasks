package eti.isa.task1.datastore;

import org.springframework.stereotype.Component;

import eti.isa.task1.engines.entity.engine;
import eti.isa.task1.engines.entity.producer;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Component
public class DataStore {

    // Set of all available producers
    private Set<producer> producers = new HashSet<>();

    // Set of all available engines
    private Set<engine> engines = new HashSet<>();

    // Seeks for all producers
    public synchronized List<producer> findAllProducers() {
        return new ArrayList<>(producers);
    }

    // Seeks for all engines
    public synchronized List<engine> findAllEngines() {
        return new ArrayList<>(engines);
    }

    // Find producer with given name
    public synchronized Optional<producer> findProducer(String name) {
        return producers.stream()
                .filter(producer -> producer.getName().equals(name))
                .findFirst();
    }

    // Find engine with given name
    public synchronized Optional<engine> findEngine(String name) {
        return engines.stream()
                .filter(engine -> engine.getName().equals(name))
                .findFirst();
    }

    // Create producer 
    public synchronized void createProducer(producer producer) throws IllegalArgumentException {
        findProducer(producer.getName()).ifPresentOrElse(
            original -> {
                throw new IllegalArgumentException(
                    String.format("The brand name \"%s\" is not unique", producer.getName()));
                }, () -> producers.add(producer));
    }

    // Create engine 

    // Delete producer with given name

    // Delete engine with given name

    // Update producer

    // Update engine

}
