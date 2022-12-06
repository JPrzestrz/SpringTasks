package eti.isa.task1.producer.event;

import org.springframework.stereotype.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import eti.isa.task1.producer.entity.Producer;

@Repository
public class ProducerEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public ProducerEventRepository(@Value("${engines.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Producer prod) {
        restTemplate.delete("/producers/{name}", prod.getName());
    }

    public void create(Producer prod) {
        restTemplate.postForLocation("/producers", PostProducerRequest.entityToDtoMapper().apply(prod));
    }

}
