package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.model.Trip;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.BookingService;



@Controller
public class BookingController {

    @Autowired
    private BookingService BookingService;

    
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
        //save ticket to database
        BookingService.saveTicket(booking);
        return "redirect:/";
    }

    @GetMapping("/user/viewTicket")
    public String viewTicket (Model model){
            
    
        return "show_book";
    }


    
    @GetMapping("/user/cancelBooking/{id}")
	public String cancelMyBooking(@PathVariable ( value = "id") long id, Model model) {

        Booking booking = BookingService.getById(id);
	
		//model.addAttribute("bookingList", bookingList);
		
		return "my_booking";
	}




   
}
