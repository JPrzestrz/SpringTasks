package eti.isa.task1.engines.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import eti.isa.task1.engines.dto.CreateEngineRequest;
import eti.isa.task1.engines.dto.GetEngineResponse;
import eti.isa.task1.engines.dto.GetEnginesResponse;
import eti.isa.task1.engines.dto.UpdateEngineRequest;
import eti.isa.task1.engines.entity.Engine;
import eti.isa.task1.engines.service.EngineService;
import eti.isa.task1.engines.service.ProducerService;

@RestController
@RequestMapping("api/engines")
public class EngineController {
    private EngineService engineService;
    private ProducerService producerService;

    @Autowired
    public EngineController(EngineService engineService, ProducerService producerService) {
        this.engineService = engineService;
        this.producerService = producerService;
    }

    @GetMapping
    public ResponseEntity<GetEnginesResponse> getEngines() {
        return ResponseEntity.ok(GetEnginesResponse.entityToDtoMapper().apply(engineService.findAll()));
    }

    @GetMapping("{name}")
    public ResponseEntity<GetEngineResponse> getEngine(@PathVariable("name") String name) {
        Optional<Engine> engine = engineService.find(name);
        return engine.map(value -> ResponseEntity.ok(GetEngineResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createEngine(@RequestBody CreateEngineRequest request, UriComponentsBuilder builder) {
        Engine engine = CreateEngineRequest
                .dtoToEntityMapper(name -> producerService.find(name).orElseThrow())
                .apply(request);
        engine = engineService.create(engine);
        return ResponseEntity.created(builder.pathSegment("api","engines","{name}").buildAndExpand(engine.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> updateEngine(@RequestBody UpdateEngineRequest request, @PathVariable("name") String name) {
        Optional<Engine> engine = engineService.find(name);
        if (engine.isPresent()) {
            UpdateEngineRequest.dtoToEntityUpdater().apply(engine.get(), request);
            engineService.update(engine.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
