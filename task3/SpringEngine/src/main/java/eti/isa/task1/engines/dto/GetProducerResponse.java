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
import eti.isa.task1.engines.entity.Producer;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
public class GetProducerResponse {

    private String name;

    public static Function<Producer, GetProducerResponse> entityToDtoMapper() {
        return producer -> GetProducerResponse.builder()
                .name(producer.getName())
                .build();
    }
}
