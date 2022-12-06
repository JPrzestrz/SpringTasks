package eti.isa.task1.engines.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import eti.isa.task1.engines.entity.Producer;

@Repository
public class ProducerEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public ProducerEventRepository(@Value("${engines.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Producer producer) {
        restTemplate.delete("/producers/{name}", producer.getName());
    }

    public void create(Producer producer) {
        restTemplate.postForLocation("/producers", PostProducerRequest.entityToDtoMapper().apply(producer));
    }
}
