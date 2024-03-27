package com.eazybytes.eazyschool.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {
	
	// We're able to display the username and roles with the help of Authentication Object 
	// We will return a view dashboard.html along with the username and roles 
	@RequestMapping("/dashboard")
	public String displayDashboard(Model model, Authentication authentication) {
		
		model.addAttribute("username", authentication.getName());
		model.addAttribute("roles",authentication.getAuthorities().toString());
		// This is just an example to show case where The GlobalExceptionController class will execute when there is an error in the application 
//		throw new RuntimeException("It's been a bad day!!");
		return "dashboard.html";
	}
}
