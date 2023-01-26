package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository <Booking, Long>   {

    
}
