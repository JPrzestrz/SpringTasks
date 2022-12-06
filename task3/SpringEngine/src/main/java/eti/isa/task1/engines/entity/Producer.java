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
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

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

    @OneToMany(cascade=CascadeType.REMOVE, mappedBy="producer")
    @ToString.Exclude
    private List<Engine> engines;
}
