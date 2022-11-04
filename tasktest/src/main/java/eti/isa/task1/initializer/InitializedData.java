/*
package eti.isa.task1.initializer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eti.isa.task1.engines.entity.Engine;
import eti.isa.task1.engines.entity.Producer;
import eti.isa.task1.engines.service.EngineService;
import eti.isa.task1.engines.service.ProducerService;

public class InitializedData {
    private final EngineService engineService;
    private final ProducerService producerService;

    @Autowired
    public InitializedData(EngineService engineService, ProducerService producerService) {
        this.engineService = engineService;
        this.producerService = producerService;
    }

    @PostConstruct
    private synchronized void init() {
        Producer audi = Producer.builder().name("Audi").build();
        producerService.create(audi);

        Engine tdi = Engine.builder()
                .name("TDI")
                .capacity(0)
                .year(2000)
                .producer(audi)
                .build();
        engineService.create(tdi);
    }
}
*/
