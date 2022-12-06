package eti.isa.task1.producer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eti.isa.task1.producer.entity.Producer;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, String> {

}
