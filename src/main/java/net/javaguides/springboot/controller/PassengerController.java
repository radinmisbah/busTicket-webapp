package net.javaguides.springboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Booking;
import net.javaguides.springboot.model.Trip;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.BookingService;
import net.javaguides.springboot.service.QrCodeService;
import net.javaguides.springboot.service.TripService;
import net.javaguides.springboot.service.UserService;

@Controller
public class PassengerController {

    @Autowired
    private TripService tripService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private QrCodeService qrCodeService;

	@Autowired
    private AsyncTaskExecutor asyncTaskExecutor;

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
		Map<Long, Long> availableSeatsMap = new HashMap<>();

		for(Trip trip: tripList) {
            long availableSeats = trip.getMaxSeat() - bookingService.countUnavailableByTripId(trip.getId());
			availableSeatsMap.put(trip.getId(), availableSeats);
        }
	    
		model.addAttribute("tripList", tripList);
		model.addAttribute("availableSeatsMap", availableSeatsMap);

		//get current user
		User currentUser = userService.getCurrentUser();
		model.addAttribute("user", currentUser);

		return "view_trip_list";
	}

	@GetMapping("/user/bookTrip/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get trip from the service
		Trip trip = tripService.getBusById(id);

		//get current user
		User currentUser = userService.getCurrentUser();
		
		//create Booking
		Booking booking = new Booking();
		booking.setTrip(trip);
		booking.setUser(currentUser);
		booking.setFinalPrice(trip.getPrice());

		//generate seat number
		ArrayList<Integer> seatNum = new ArrayList<Integer>();

		ArrayList<Integer> occupideSeat = (ArrayList<Integer>) bookingService.getOccupiedSeat(trip.getId());

		for (int i = 1; i <= trip.getMaxSeat(); i++) {

			if(!occupideSeat.contains(i)){
				seatNum.add(i);
			}
	}
		// set bus as a model attribute to pre-populate the form
		model.addAttribute("booking", booking);
		model.addAttribute("seatNum",seatNum);
		return "book_trip";
	}

	@GetMapping("/user/myBooking")
	public String showMyBooking(Model model) {
 
		//get current user
		User currentUser = userService.getCurrentUser();
		Long userId = currentUser.getId();
        List<Booking> bookingList = bookingService.getAllBookingByUser(userId);
        
		model.addAttribute("bookingList", bookingList);
		
		return "my_booking";
	}

	@GetMapping("/user/cancelBooking/{id}")
	public String cancelMyBooking(@PathVariable ( value = "id") long id, Model model) {

        Booking booking = bookingService.getById(id);
	
		booking.setBookingStatus("Cancel");
		bookingService.saveTicket(booking);
		
		return "redirect:/user/myBooking";
	}

	@GetMapping("/user/BookingHistory")
	public String showBookingHistory(Model model) {
 
		//get current user
		User currentUser = userService.getCurrentUser();
		Long userId = currentUser.getId();
        List<Booking> bookingList = bookingService.getBookingHistoryForId(userId);
        
		model.addAttribute("bookingList", bookingList);
		
		return "booking_history";
	}

	@GetMapping("/user/testQR")
	
	public String testCreateQR(Model model) {
       
		qrCodeService.createQr().join();
        model.addAttribute("qrCodeImage", "tempTicketQR.png");
      
		return "view_QR";
	}
    
    
}
