package net.javaguides.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.service.BookingService;



@Controller
public class BookingController {

    @Autowired
    private BookingService BookingService;

    @Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
    //display list of sold ticket
    @GetMapping("/view")
    public String viewHomePage (Model model){
            
        
        model.addAttribute("listBooking", BookingService.getAllBooking());
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


      
        System.out.println("Secret code:"+passwordEncoder.encode(booking.toString()));
        //save ticket to database
        BookingService.saveTicket(booking);
        return "redirect:/user/myBooking";
    }

    @GetMapping("/user/viewTicket")
    public String viewTicket (Model model){
            
    
        return "show_book";
    }


    
    




   
}
