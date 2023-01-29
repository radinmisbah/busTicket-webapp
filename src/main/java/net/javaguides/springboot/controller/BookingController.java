package net.javaguides.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.service.BookingService;



@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
    //display list of sold ticket
    @GetMapping("/view")
    public String viewHomePage (Model model){
            
        
        model.addAttribute("listBooking", bookingService.getAllBooking());
        return "index";
    }

    @GetMapping("/showNewTicketForm")
    public String showNewTicketForm(Model model){
        //create model attrobute to bind form data
        Booking Booking = new Booking();
        model.addAttribute("Booking", Booking);

        return "new_ticket";

    }

    @PostMapping("/user/saveTicket")
    public String saveTicket(@ModelAttribute("booking") Booking booking){

        
        booking.setBookingStatus("Paid");

        booking.setQrCode(passwordEncoder.encode(booking.toString()));

        //save ticket to database
        bookingService.saveTicket(booking);

        return "redirect:/user/myBooking";
    }

    @GetMapping("/user/viewTicket/{id}")
    public String viewTicket (@PathVariable ( value = "id") long id, Model model){
            
        Booking booking = bookingService.getById(id);
        model.addAttribute("booking", booking);

        return "show_book";
    }


    
    




   
}
