package eti.isa.task1.engines.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class engine implements Serializable {

    // Engine's identification
    private Long id;

    // Engine's name
    private String name;

    // Engine's capacity in cm^3
    private int capacity;

    // Engine's production year
    private int year;

    // Engine's price in pln
    private int price;

}
