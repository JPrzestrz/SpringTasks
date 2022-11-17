package eti.isa.task1.engines.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import eti.isa.task1.engines.dto.GetEngineResponse;
import eti.isa.task1.engines.dto.GetEnginesResponse;
import eti.isa.task1.engines.dto.PostEngineRequest;
import eti.isa.task1.engines.dto.PutEngineRequest;
import eti.isa.task1.engines.entity.Engine;
import eti.isa.task1.engines.entity.Producer;
import eti.isa.task1.engines.service.EngineService;
import eti.isa.task1.engines.service.ProducerService;

@RestController
@RequestMapping("api/producers/{name}/engines")
public class ProducerEngineController {
    private EngineService engineService;
    private ProducerService producerService;

    @Autowired
    public ProducerEngineController(ProducerService producerService, EngineService engineService) {
        this.producerService = producerService;
        this.engineService = engineService;
    }

    @GetMapping
    public ResponseEntity<GetEnginesResponse> getEngines(@PathVariable("name") String name) {
        Optional<Producer> producer = producerService.find(name);
        return producer.map(value -> ResponseEntity.ok(GetEnginesResponse.entityToDtoMapper().apply(engineService.findAll(value))))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetEngineResponse> getEngine(
        @PathVariable("name") String name, 
        @PathVariable("id") Long id) {
            return engineService.find(name,id)
                .map(value -> ResponseEntity.ok(GetEngineResponse.entityToDtoMapper().apply(value)))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postEngine(
        @PathVariable("name") String name,
        @RequestBody PostEngineRequest request,
        UriComponentsBuilder builder) {
            Optional<Producer> producer = producerService.find(name);
            if(producer.isPresent()) {
                Engine engine = PostEngineRequest
                    .dtoToEntityMapper(value -> producerService.find(value).orElseThrow())
                    .apply(request);
                engine = engineService.create(engine);
                return ResponseEntity.created(builder.pathSegment("api","producers","{name}","engines","{id}")
                    .buildAndExpand(producer.get().getName(), engine.getId()).toUri()).build();
            } else {
                return ResponseEntity.notFound().build();
            }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEngine(
        @PathVariable("name") String name,
        @PathVariable("id") Long id) {
            Optional<Engine> engine = engineService.find(name,id);
            if(engine.isPresent()) {
                engineService.delete(id);
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.notFound().build();
            }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putEngine(
        @PathVariable("name") String name,
        @RequestBody PutEngineRequest request,
        @PathVariable("id") Long id) {
            Optional<Engine> engine = engineService.find(name,id);
            if(engine.isPresent()) {
                PutEngineRequest.dtoToEntityUpdater().apply(engine.get(), request);
                engineService.update(engine.get());
                return ResponseEntity.accepted().build();
            } else {
                return ResponseEntity.notFound().build();
            }
    }
}
