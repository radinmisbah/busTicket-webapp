package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/saveTicket")
    public String saveTicket(@ModelAttribute("Booking") Booking Booking){
        //save ticket to database
        BookingService.saveTicket(Booking);
        return "redirect:/";
    }




   
}
