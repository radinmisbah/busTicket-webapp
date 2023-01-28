package net.javaguides.springboot.service;

import java.util.List;


import net.javaguides.springboot.model.Booking;

public interface BookingService {

    List<Booking> getAllBooking();
    void saveTicket (Booking Booking);
    int countUnavailableByTripId(Long id);
    List<Integer> getOccupiedSeat(Long id);
    List<Booking> getAllBookingByUser(Long id);
    Booking getById(Long id);
}
