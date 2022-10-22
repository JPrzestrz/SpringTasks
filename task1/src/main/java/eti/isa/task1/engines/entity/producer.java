package eti.isa.task1.engines.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

// Class for engine producer.
// Conatins name of the company, it's foundation year and tax id number NIP.
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class producer implements Serializable{
    // Company's name
    private String name;

    // Company's nip number
    private Long NIP;

    // Company's foundation year
    private int year;
}
