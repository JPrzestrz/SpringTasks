package eti.isa.task1.engines.dto;

import java.util.function.BiFunction;

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
public class UpdateEngineRequest {
    
    private int capacity;
    private int year;

    public static BiFunction<Engine, UpdateEngineRequest, Engine> dtoToEntityUpdater() {
        return (engine, request) -> {
            engine.setCapacity(request.getCapacity());
            engine.setYearz(request.getYear());
            return engine;
        };
    }
}
