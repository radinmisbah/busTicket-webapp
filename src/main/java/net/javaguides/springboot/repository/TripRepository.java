package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.javaguides.springboot.model.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByDepartureAndArrival(String departure, String arrival);

    
}
