package com.eazybytes.eazyschool.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// @ControllerAdvice is a special annotation come from @Controller 
// which allow to handle the exception across the whole project/application 
// You can intercept this annotation @ControllerAdvice with the method of using @RequestMapping and throw an error(exception) together 
@ControllerAdvice
public class GlobalExceptionController {
	
	// @ExceptionHandler is a must annotation when you want to throw an error purposely 
	// Inside the bracket, We can use "Exception" mean that All Exception will be covered the whole application
	// You can specially customize the specific exception you want 
	@ExceptionHandler(Exception.class)
	public ModelAndView exceptionHandler(Exception exception) {
		ModelAndView errorPage = new ModelAndView();
		errorPage.setViewName("error");
		// Sending the information to the UI 
		errorPage.addObject("errormsg",exception.getMessage());
		return errorPage;
	}
}
