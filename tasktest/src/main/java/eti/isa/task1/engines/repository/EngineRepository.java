package eti.isa.task1.engines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import eti.isa.task1.engines.entity.Engine;

@Repository
public interface EngineRepository extends JpaRepository<Engine, String>{
    
    Optional<Engine> findByName(String name);
    List<Engine> findAll();
}
