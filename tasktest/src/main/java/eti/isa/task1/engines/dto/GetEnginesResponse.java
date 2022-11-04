package eti.isa.task1.engines.dto;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
public class GetEnginesResponse {
    
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @ToString
    @EqualsAndHashCode
    public static class Engine {
        private String name;
    }

    @Singular
    private List<Engine> engines; 

    public static Function<Collection<eti.isa.task1.engines.entity.Engine>,GetEnginesResponse> entityToDtoMapper() {
        return engines -> {
            GetEnginesResponseBuilder response = GetEnginesResponse.builder();
            engines.stream()
                    .map(engine -> Engine.builder()
                        .name(engine.getName())
                        .build())
                    .forEach(response::engine);
            return response.build();
        };
    }

}
