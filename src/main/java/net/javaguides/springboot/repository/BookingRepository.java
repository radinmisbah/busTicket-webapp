package net.javaguides.springboot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import net.javaguides.springboot.model.Booking;

public interface BookingRepository extends JpaRepository <Booking, Long>   {

    @Query(value = "SELECT COUNT(*) FROM bookings WHERE trip_id = :tripId AND status = 'Paid'", nativeQuery = true)
    public int countByTripIdAndPaid(@Param("tripId") Long tripId);

    @Query(value = "SELECT * FROM bookings WHERE trip_id = :tripId AND status = 'Paid'", nativeQuery = true)
    List<Booking> findByTripIdAndStatus(@Param("tripId") Long tripId);
    
    @Query(value = "SELECT * FROM bookings WHERE purchased_by = :userId AND status = 'Paid'", nativeQuery = true)
    public List<Booking> findAllByPurchasedBy(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM bookings WHERE purchased_by = :userId AND status != 'Paid'", nativeQuery = true)
    public List<Booking> findBookingHistoryForId(@Param("userId") Long userId);

    public Booking findByQrCode(@Param("qr") String qr);
}
