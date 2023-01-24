package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguides.springboot.model.SoldTicket;
import net.javaguides.springboot.service.SoldTicketService;



@Controller
public class SoldTicketController {

    @Autowired
    private SoldTicketService soldTicketService;
    
    //display list of sold ticket
    @GetMapping("/view")
    public String viewHomePage (Model model){
            
        
        model.addAttribute("listSoldTicket", soldTicketService.getAllSoldTicket());
        return "index";
    }

    @GetMapping("/showNewTicketForm")
    public String showNewTicketForm(Model model){
        //create model attrobute to bind form data
        SoldTicket soldTicket = new SoldTicket();
        model.addAttribute("soldTicket", soldTicket);
        return "new_ticket";

    }

    @PostMapping("/saveTicket")
    public String saveTicket(@ModelAttribute("soldTicket") SoldTicket soldTicket){
        //save ticket to database
        soldTicketService.saveTicket(soldTicket);
        return "redirect:/";
    }




   
}
