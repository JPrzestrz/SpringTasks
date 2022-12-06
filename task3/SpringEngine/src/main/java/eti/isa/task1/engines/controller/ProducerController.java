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

import eti.isa.task1.engines.dto.GetProducerResponse;
import eti.isa.task1.engines.dto.GetProducersResponse;
import eti.isa.task1.engines.dto.PostProducerRequest;
import eti.isa.task1.engines.dto.PutProducerRequest;
import eti.isa.task1.engines.entity.Producer;
import eti.isa.task1.engines.service.ProducerService;

@RestController
@RequestMapping("api/producers")
public class ProducerController {
    private ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping
    public ResponseEntity<GetProducersResponse> getProducers() {
        return ResponseEntity.ok(GetProducersResponse.entityToDtoMapper().apply(producerService.findAll()));
    }

    @GetMapping("{name}")
    public ResponseEntity<GetProducerResponse> getProducer(@PathVariable("name") String name) {
        Optional<Producer> producer = producerService.find(name);
        return producer.map(value -> ResponseEntity.ok(GetProducerResponse.entityToDtoMapper().apply(value)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postProducer(@RequestBody PostProducerRequest request, UriComponentsBuilder builder) {
        Producer producer = PostProducerRequest
            .dtoToEntityMapper()
            .apply(request);
        producer = producerService.create(producer);
        return ResponseEntity.created(builder.pathSegment("api","producers","{name}")
            .buildAndExpand(producer.getName()).toUri()).build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteProducer(@PathVariable("name") String name) {
        Optional<Producer> producer = producerService.find(name);
        if(producer.isPresent()) {
            producerService.delete(producer.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> putProducer(@RequestBody PutProducerRequest request, @PathVariable("name") String name) {
        Optional<Producer> producer = producerService.find(name);
        if(producer.isPresent()) {
            PutProducerRequest.dtoToEntityUpdater().apply(producer.get(), request);
            producerService.update(producer.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
