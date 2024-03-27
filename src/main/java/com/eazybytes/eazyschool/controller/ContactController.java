package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
@Controller
public class ContactController {

	private static final Logger log = LoggerFactory.getLogger(ContactController.class);

	
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
     
    
    //  when the user want to access the page "Contact", There will be a form where the end-user can fill the form and send the data into Database
    //  In order to do that, we use @ModelAttribute so that The data which the end-user fill it will bind to the object contact 
    @GetMapping(value = "/contact")
    public String displayContactPage(@ModelAttribute Contact contact,Model model) {
        model.addAttribute("contact", contact);
        return "contact.html";
    }

    /*@RequestMapping(value = "/saveMsg",method = POST)
    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,
                                    @RequestParam String email, @RequestParam String subject, @RequestParam String message) {
        log.info("Name : " + name);
        log.info("Mobile Number : " + mobileNum);
        log.info("Email Address : " + email);
        log.info("Subject : " + subject);
        log.info("Message : " + message);
        return new ModelAndView("redirect:/contact");
    }*/
    
    // In here, Because We have already use the @ModelAttribute in the function displayContactPage 
    // We use @Valid to check the data preventing bad data storing inside the database, we're also including the BindingResult 
    // BindingResult will started after it confirm is there any errors inside the Object "contact" by using Hibernate Validation 
    // If the bindingResult has the errors, it will return the view "contact.html" and display the error message (in Contact.java POJO class) in view. 
    @PostMapping("/saveMsg")
    public String saveMessage(@Valid Contact contact, BindingResult bindingResult){
    	if (bindingResult.hasErrors()) {
    		return "contact.html";
    	}
        contactService.saveMessageDetails(contact);
        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model) {
    	List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
    	ModelAndView modelAndView = new ModelAndView("messages.html");
    	modelAndView.addObject("contactMsgs",contactMsgs);
    	return modelAndView;
    }
    
    @RequestMapping(value = "/closeMsg", method = RequestMethod.GET)
    public String closeMsg(@RequestParam int id) {
    	contactService.updateMsgStatus(id);
    	return "redirect:/displayMessages";
    }
    
}