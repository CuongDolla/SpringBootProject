package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	
	// This function will let us easily configure the Controller
	// Instead of write code @RequestMapping(value = "...") and then return just 1 view
	// We can optimize that with just 1 line of code with function addViewControllers(ViewControllerRegistry registry) 
	// Note: It is recommend to use this function addViewControllers when we don't want to add business logic along with the page
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/courses").setViewName("courses");
        registry.addViewController("/about").setViewName("about");
    }

}
