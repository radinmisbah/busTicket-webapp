package net.javaguides.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public List<Booking> getAllBooking() {
        // TODO Auto-generated method stub
        return bookingRepository.findAll();
    }

    @Override
    public void saveTicket(Booking Booking) {
        this.bookingRepository.save(Booking);
        
    }

    @Override
    public int countUnavailableByTripId(Long id) {
        // TODO Auto-generated method stub
        return bookingRepository.countByTripIdAndPaid(id);
    }

    @Override
    public List<Integer> getOccupiedSeat(Long id) {
        List<Booking> bookingList = bookingRepository.findByTripIdAndStatus(id);

        ArrayList<Integer> occupiedSeat = new ArrayList<Integer>();
		for (Booking booking : bookingList) {
            int seat = booking.getSeatNumber();
            occupiedSeat.add(seat);
        }
        
        return occupiedSeat;
    }
    
}
