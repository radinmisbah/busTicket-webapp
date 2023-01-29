package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.service.UserService;

@Controller
public class AdminController {
    @Autowired
    private UserService userService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/administrator")
	public String showManageCompany(Model model) {
        
		return findPaginated(1, "id", "asc", model);		
	}

    @GetMapping("/admin/administrator/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
            @RequestParam("sortField") String sortField,
            @RequestParam("sortDir") String sortDir,
            Model model) {
        int pageSize = 15;
        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
        
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("adminList", page.getContent());
        model.addAttribute("newAdmin", new User());
        
        return "administrator_index";
    }
}
