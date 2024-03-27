package com.eazybytes.eazyschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.service.PersonService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("public")
public class PublicController {
	
	@Autowired
	PersonService personService;
	
	@RequestMapping(value = "/register", method = {RequestMethod.GET})
	public String displayRegisterPage(@ModelAttribute("person") Person person, Model model) {
		model.addAttribute("person", person);
		return "register.html";
	}
	
	@RequestMapping(value = "/createUser", method = {RequestMethod.POST})
	public String createUser(@Valid Person person, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "register.html";
		}
		boolean isSaved = personService.createNewPerson(person);
		if(isSaved) {
			return "redirect:/login?register=true";
		}
		else {
			return "register.html";
		}
		//return "redirect:/login?register=true";
	}
}
