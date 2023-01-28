package net.javaguides.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import net.javaguides.springboot.service.UserService;

@Controller
public class MainController {

	@Autowired
    private UserService userService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
    public String redirectByRole() {

        if (userService.getCurrentUser().getRole().equals("ROLE_ADMIN")) {
            return "redirect:/admin/companies";
        } else if (userService.getCurrentUser().getRole().equals("ROLE_USER")) {
            return "redirect:/user/mainSearchTrip";
        }
        return "redirect:/";
    }
}
