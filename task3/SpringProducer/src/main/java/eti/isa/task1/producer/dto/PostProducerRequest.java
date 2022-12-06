package eti.isa.task1.producer.dto;

import java.util.function.Function;

import eti.isa.task1.producer.entity.Producer;
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
    private int nip;
    private int yearz;

    public static Function<PostProducerRequest, Producer> dtoToEntityMapper() {
        return request -> Producer.builder()    
            .name(request.getName())
            .nip(request.getNip())
            .yearz(request.getYearz())
            .build();
    }
}
