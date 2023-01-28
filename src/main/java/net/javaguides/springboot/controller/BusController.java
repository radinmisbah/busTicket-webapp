package net.javaguides.springboot.controller;

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
import net.javaguides.springboot.model.Bus;
import net.javaguides.springboot.service.BusService;
import net.javaguides.springboot.service.CompanyService;

@Controller
public class BusController {
    @Autowired
	private BusService busService;

    @Autowired
    private CompanyService companyService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/buses")
	public String showManageBus(Model model) {

		return findPaginated(1, "id", "asc", model);		
	}

    @GetMapping("/admin/buses/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model) {
        int pageSize = 15;
        
        Page<Bus> page = busService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Bus> registeredBuses = page.getContent();
        
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("registeredBuses", registeredBuses);
        model.addAttribute("bus", new Bus());
        model.addAttribute("companies", companyService.findAll());
        
        return "bus_index";
    }

    @PostMapping("/admin/buses/save")
	public String saveBus(@ModelAttribute("registeredBus") Bus bus, BindingResult result) {
        if(result.hasErrors()) {

        } else {
            busService.saveBus(bus);
        }

		return "redirect:/admin/buses";
	}

    @GetMapping("/admin/buses/{id}/delete")
	public String deleteCompany(@PathVariable (value = "id") long id) {
		busService.deleteBusById(id);

		return "redirect:/admin/buses";
	}
}
