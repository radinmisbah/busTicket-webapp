package net.javaguides.springboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	public String searchTicket(Model model, @RequestParam("departure") String departure, @RequestParam("arrival") String arrival, @RequestParam("departureDate") String departureDate) throws ParseException{	
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(departureDate);
		List<Trip> tripList = tripService.searchTrip(departure, arrival, date);
	    
		model.addAttribute("tripList", tripList);
		
		return "view_trip_list";
	}

	@PostMapping("/user/findTicket")
	public String findTicket(Model model, @RequestParam("departure") String departure, @RequestParam("arrival") String arrival,@RequestParam("departureDate") String departureDate) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date date = formatter.parse(departureDate);
    //use date to find the ticket
	System.out.println(date);
    List<Trip> tripList = tripService.findbydate(date) ;
    
	model.addAttribute("tripList",tripList);
    return "view_trip_list";
}





    
    
}
