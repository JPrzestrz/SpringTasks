package eti.isa.task1.engines.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.function.Function;

import eti.isa.task1.engines.entity.Producer;
import eti.isa.task1.engines.entity.Engine;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
public class CreateEngineRequest {

    private String name;
    private int capacity;
    private int year;
    private String producer;

    public static Function<CreateEngineRequest, Engine> dtoToEntityMapper(
        Function<String, Producer> producerFunction) {
        return request -> Engine.builder()
            .name(request.getName())
            .capacity(request.getCapacity())
            .year(request.getYear())
            .producer(producerFunction.apply(request.getProducer()))
            .build();
    }
}
