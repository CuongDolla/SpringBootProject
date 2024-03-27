package com.eazybytes.eazyschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {
	
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		// We need to use the Object MvcRequestMatcher.Builder because We have declared H2 Database which have another DispatchServlet 
		// Because Spring don't know how to tell the difference between 2 DispatchServlet 
		// So We have to use the Objecy MvcRequestMathcer.Builder
//		MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
		// The older version for Spring Security version 6.0
		// The Spring Boot team recommened us to use Lambda DSL
		
		// The traditional way: 
//		http.authorizeHttpRequests().anyRequest().permitAll()
//			.and().formLogin()
//			.and().httpBasic();
//		
//		return http.build();
		
		// Permit All Requests inside the Web Application ( Note that we're using Lambda DLS for Spring Security version 6.0+
		// The reason we're using Lambda DLS is that Spring Team will eradicate the traditional way in Spring Security 7.0
		// The function csrf.disable only work when we are using Angualar, React front-end framework 
		// In fact, Because We have implemented Thymeleaf. Thymeleaf will do its job to prevent CSRF attack 
		// Here, The Spring  Security alreay implement CSRF protection to all request but Only those that can changed the data in backend server like method POST, UPDATE,DELETE but not GET
		// The reason we have .ignoringRequestMatchers("/saveMsg") is that It only save the data to the backend server, not change or edit the data
		// There is a problem when we enable the csrf protection. We need to implement a method in login, logout form so whenever we do the login,logout. It generate the website token AND CSRF token
		http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveMsg")
								.ignoringRequestMatchers("public/**"))
		.authorizeHttpRequests((requests) -> requests
												// This is crucial so that CSS, JavaScript can be activated, included
												.requestMatchers("/dashboard").authenticated()
												.requestMatchers("/displayMessages").hasRole("ADMIN")
												.requestMatchers("/closeMsg/**").hasRole("ADMIN")
												.requestMatchers("/").permitAll()
												.requestMatchers("").permitAll()
												.requestMatchers("/home").permitAll()
												.requestMatchers("/holidays").permitAll()
												.requestMatchers("/contact").permitAll()
												.requestMatchers("/saveMsg").permitAll()
												.requestMatchers("/courses").permitAll()
												.requestMatchers("/about").permitAll()
												.requestMatchers("/assets/**").permitAll()
												.requestMatchers("/login").permitAll()
												.requestMatchers("/logout").permitAll()
												.requestMatchers("/public/**").permitAll())
												// This is crucial so that CSS, JavaScript can be activated, included
												// Instead let the Spring Security do the work, we will manually configure the page login.html 
												// If the end-user successfully authenticated. It will redirect to the page dashboard.html 
												// Upon failure, We will use Query method (RequestParams) instead of build another page login_failutre.html
												// Remember, We need the end-user to see the result -> function permitAll() 
// 												.formLogin(Customizer.withDefaults())
												.formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
																	.defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
												// Opposite of LogIn, We also need to handle LogOut 
												// When the end-user log out the website, The session store inside the database must be removed once the user log out
												// That why, we need invalidateHttpSession(true) and the function permitAll() to let end-user see the result. 
												.logout((logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
																	.invalidateHttpSession(true).permitAll()))
												.httpBasic(Customizer.withDefaults());
//		http.headers(headersConfigurer -> headersConfigurer
//                .frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()));
//											
//		http.formLogin(Customizer.withDefaults());
//		http.httpBasic(Customizer.withDefaults());						
		return http.build();
	}
	
	// This function will hold multiple user for login instead of 1 user in application.properites
	// However Ideally The users login is always store in the database 
	// NOTE: THIS METHOD ONLY STORE MULTIPLE USER WITHOUT DATABASE
	// IT IS RECOMMENED TO STORE USER ACCOUNT IN THE DATABASE, ESPECIALLY H2 DATABASE
	
//	  @Bean
//	  public InMemoryUserDetailsManager userDetailsService() {
//
//	        UserDetails user = User.builder()
//	                .username("user")
//	                .password(passwordEncoder().encode("12345"))
//	                .roles("USER")
//	                .build();
//	        UserDetails admin = User.builder()
//	                .username("admin")
//	                .password(passwordEncoder().encode("54321"))
//	                .roles("ADMIN")
//	                .build();
//	        return new InMemoryUserDetailsManager(user, admin);
//	    }
	// Another note that: Spring Security 6.2 has deprecated the function withDefaultPasswordEncoder() 
	// So Instead, we are using the function PasswordEncoder()  
	// with the new function BCryptPasswordEncoder() that will take care of encoding the account and password ( plain text -> cipher text) 
//	@Bean 
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
}
