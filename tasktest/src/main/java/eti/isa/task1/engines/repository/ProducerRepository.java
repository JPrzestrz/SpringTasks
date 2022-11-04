package eti.isa.task1.engines.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eti.isa.task1.engines.entity.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, String> {

}
