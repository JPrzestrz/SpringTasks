package eti.isa.task1.engines.event;

import java.util.function.Function;

import eti.isa.task1.engines.entity.Producer;
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
public class PostProducerRequest {
    private String name;

    public static Function<Producer, PostProducerRequest> entityToDtoMapper() {
        return entity -> PostProducerRequest.builder()
            .name(entity.getName())
            .build();
    }
}
