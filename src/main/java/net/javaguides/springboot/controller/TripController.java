package net.javaguides.springboot.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import net.javaguides.springboot.model.Trip;
import net.javaguides.springboot.service.TripService;

@Controller
public class TripController {

    @Autowired
	private TripService tripService;

    //redirect to manage bus page
	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/viewManageBus")
	public String viewManageBus(Model model) {
		return findPaginated(1, "busName", "asc", model);		
	}

    //redirect to form to add new bus
    @GetMapping("/showAddBusForm")
	public String showAddBusForm(Model model) {
		// create model attribute to bind form data
		Trip bus = new Trip();
		model.addAttribute("bus", bus);
		return "add_bus_form";
	}

    //do http post request to save new bus info
    @PostMapping("/saveBus")
	public String saveNewBus(@ModelAttribute("bus") Trip bus) {
		// save bus to database
		tripService.create(bus);
		return "redirect:/admin/viewManageBus";
	}

    @GetMapping("/showBusUpdateForm/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get bus from the service
		Trip bus = tripService.getBusById(id);
		
		// set bus as a model attribute to pre-populate the form
		model.addAttribute("bus", bus);
		return "update_bus_form";
	}

    @GetMapping("/deleteBus/{id}")
	public String deletebus(@PathVariable (value = "id") long id) {
		
		// call delete bus method 
		tripService.deleteBusById(id);
		return "redirect:/admin/viewManageBus";
	}

	//mapping for page if the the list too long
	@GetMapping("/admin/manage_bus/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Trip> page = tripService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Trip> listBus = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listBus", listBus);
		return "manage_bus";
	}


	//controller mapping for user page	
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
