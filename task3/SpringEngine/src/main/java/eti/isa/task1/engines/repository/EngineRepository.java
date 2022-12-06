package eti.isa.task1.engines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import eti.isa.task1.engines.entity.Engine;
import eti.isa.task1.engines.entity.Producer;

@Repository
public interface EngineRepository extends JpaRepository<Engine, Long>{
    
    Optional<Engine> findByName(String name);
    Optional<Engine> findById(Long id);
    List<Engine> findAll();
    List<Engine> findAllByProducer(Producer producer);
    Optional<Engine> findByIdAndProducer(Long id, Producer producer);
}
