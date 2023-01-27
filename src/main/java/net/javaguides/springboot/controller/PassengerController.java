package net.javaguides.springboot.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.model.Trip;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.TripService;

@Controller
public class PassengerController {

    @Autowired
    private TripService tripService;

    @PreAuthorize("hasRole('ROLE_USER')")
    //redirect to form to search new bus
    @GetMapping("/user/mainSearchTrip")
	public String showSearchBusForm(Model model) {
		// create model attribute to bind form data
		Trip trip = new Trip();
		model.addAttribute("trip", trip);

		return "search_trip";
	}

    //controller mapping for user page	
	@GetMapping("/user/viewTripList")
	public String searchTicket(Model model, @RequestParam("departure") String departure, @RequestParam("arrival") String arrival, @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date departureDate) {
		List<Trip> tripList = tripService.searchTrip(departure, arrival,departureDate);
		model.addAttribute("tripList", tripList);
		
		return "view_trip_list";
	}

    
    
}
