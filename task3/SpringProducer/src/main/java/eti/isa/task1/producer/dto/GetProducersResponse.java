package eti.isa.task1.producer.dto;

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
public class GetProducersResponse {
    
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @ToString
    @EqualsAndHashCode
    public static class Producer {
        private String name;
    }

    @Singular
    private List<Producer> producers; 

    public static Function<Collection<eti.isa.task1.producer.entity.Producer>,GetProducersResponse> entityToDtoMapper() {
        return producers -> {
            GetProducersResponseBuilder response = GetProducersResponse.builder();
            producers.stream()
                    .map(producer -> Producer.builder()
                        .name(producer.getName())
                        .build())
                    .forEach(response::producer);
            return response.build();
        };
    }
}
