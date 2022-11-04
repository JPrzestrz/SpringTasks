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

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// Class for engine producer.
// Conatins name of the company, it's foundation year and tax id number NIP.
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "producers")
public class Producer implements Serializable{
    
    // Company's name
    @Id
    private String name;

    // Company's nip number
    private int nip;

    // Company's foundation year
    private int yearz;

}
