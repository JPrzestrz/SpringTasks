package eti.isa.task1.engines.dto;

import java.util.function.Function;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import eti.isa.task1.engines.entity.Engine;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
public class GetEngineResponse {
    
    private String name;
    private int capacity;
    private int year;
    private String producer;

    public static Function<Engine, GetEngineResponse> entityToDtoMapper() {
        return engine -> GetEngineResponse.builder()
            .name(engine.getName())
            .capacity(engine.getCapacity())
            .year(engine.getYear())
            .producer(engine.getProducer().getName())
            .build();
    }
}
