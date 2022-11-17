package eti.isa.task1.engines.dto;

import java.util.function.BiFunction;

import eti.isa.task1.engines.entity.Engine;
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
public class PutEngineRequest {
    private String name;
    private int capacity;
    private int yearz;
    public static BiFunction<Engine, PutEngineRequest, Engine> dtoToEntityUpdater() {
        return (engine, request) -> {
            engine.setName(request.getName());
            engine.setYearz(request.getYearz());
            engine.setCapacity(request.getCapacity());
            return engine;
        };
    }
}
