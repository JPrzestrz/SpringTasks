package eti.isa.task1.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<Ent, Key> {

    // Find entity using it's primary key 
    Optional<Ent> find(Key id);

    // Find all entities
    List<Ent> findAll();

    // Store new object
    void create(Ent entity);

    // Delete object
    void delete(Ent entity);

    // Update 
    void update(Ent entity);
    
}
