package eti.isa.task1.engines.dto;

import java.util.function.Function;

import eti.isa.task1.engines.entity.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
public class PostEngineRequest {
    private String name;
    // Engine's capacity in cm^3
    private int capacity;
    // Engine's production year
    private int yearz;
    //
    private String producer;

    public static Function<PostEngineRequest, Engine> dtoToEntityMapper(
        Function<String,Producer> producerFunction) {
            return request -> Engine.builder()
                .name(request.getName())
                .capacity(request.getCapacity())
                .yearz(request.getYearz())
                .producer(producerFunction.apply(request.getProducer()))
                .build();
        }
}
