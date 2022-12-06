package eti.isa.task1.producer.dto;

import java.util.function.BiFunction;

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
public class PutProducerRequest {
    private int nip;
    private int yearz;
    public static BiFunction<Producer, PutProducerRequest, Producer> dtoToEntityUpdater() {
        return(producer,request) -> {
            producer.setNip(request.getNip());
            producer.setYearz(request.getYearz());
            return producer;
        };
    }
}
