package net.javaguides.springboot.service;

import java.util.List;


import net.javaguides.springboot.model.Booking;

public interface BookingService {

    List<Booking> getAllBooking();
    void saveTicket (Booking Booking);
    
}