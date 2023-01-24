package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Bus;

@Repository
public interface BusRepository extends JpaRepository <Bus, Long> {

    List<Bus> findByDepartureAndArrival(String departure, String arrival);

    
}
