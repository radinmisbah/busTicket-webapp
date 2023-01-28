package net.javaguides.springboot.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import net.javaguides.springboot.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByDepartureAndArrival(String departure, String arrival);
    List<Trip> findByDepartureAndArrivalAndDepartureDate(String departure, String arrival, Date departureDate);
    List<Trip> findAllByDepartureDate (Date departureDate);

    
}
