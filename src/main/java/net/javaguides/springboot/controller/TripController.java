package net.javaguides.springboot.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import net.javaguides.springboot.model.Trip;
import net.javaguides.springboot.service.BusService;
import net.javaguides.springboot.service.TripService;

@Controller
public class TripController {

    @Autowired
	private TripService tripService;

	@Autowired
	private BusService busService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/trips")
	public String showManageTrip(Model model) {
		return findPaginated(1, "id", "asc", model);		
	}

	@GetMapping("/admin/trips/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Trip> page = tripService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Trip> tripList = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("tripList", tripList);
		model.addAttribute("newTrip", new Trip());
		model.addAttribute("buses", busService.findAll());

		return "trip_index";
	}

    @PostMapping("/admin/trips/save")
	public String saveNewTrip(@ModelAttribute("newTrip") Trip trip, BindingResult result) {
		if(result.hasErrors()) {

        } else {
            tripService.create(trip);
        }

		return "redirect:/admin/trips";
	}

    @GetMapping("/admin/trips/{id}/edit")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		Trip bus = tripService.getBusById(id);
		model.addAttribute("bus", bus);

		return "update_bus_form";
	}

    @GetMapping("/admin/trips/{id}/delete")
	public String deletebus(@PathVariable (value = "id") long id) {
		tripService.deleteBusById(id);

		return "redirect:/admin/trips";
	}

	@GetMapping("/user/searchBus")
	public String searchTicket(Model model, @RequestParam("departure") String departure, @RequestParam("arrival") String arrival, @RequestParam("departureDate") Date departureDate) {
		List<Trip> buses = tripService.searchBus(departure, arrival);
		model.addAttribute("buses", buses);
		//departureDate is used for filtering availability
		return "search_bus";
	}

    @PreAuthorize("hasRole('ROLE_USER')")
    //redirect to form to search new bus
    @GetMapping("/user/showSearchBusForm")
	public String showSearchBusForm(Model model) {
		// create model attribute to bind form data
		Trip bus = new Trip();
		model.addAttribute("bus", bus);
		return "search_bus";
	}
}
