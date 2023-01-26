package net.javaguides.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository BookingRepository;

    @Override
    public List<Booking> getAllBooking() {
        // TODO Auto-generated method stub
        return BookingRepository.findAll();
    }

    @Override
    public void saveTicket(Booking Booking) {
        // TODO Auto-generated method stub
        this.BookingRepository.save(Booking);
        
    }
    
}
