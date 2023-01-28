package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository <Booking, Long>   {

    @Query(value = "SELECT COUNT(*) FROM bookings WHERE trip_id = :tripId AND booking_status = 'Paid'", nativeQuery = true)
    public int countByTripIdAndPaid(@Param("tripId") Long tripId);
    
}
