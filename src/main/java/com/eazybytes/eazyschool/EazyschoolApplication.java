package com.eazybytes.eazyschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


// The POJO class that has @SpringBoot Application will alway be the main class to activate the project
// Also Because of being a main class, We also need to included @ComponentScan 
// The reason we mention @ComponentScan is that The Spring IoC container will scan all these package in order to solve the problem " not found page 404,/error"
@SpringBootApplication
@ComponentScan(basePackages= {"com.eazybytes.eazyschool.repository"
			                ,"com.eazybytes.eazyschool.config"
		                    ,"com.eazybytes.eazyschool.service" ,"com.eazybytes.eazyschool.controller"
							,"com.eazybytes.eazyschool.model"
							,"com.eazybytes.eazyschool.constants"
							,"com.eazybytes.eazyschool.audit"
							,"com.eazybytes.eazyschool.annotation"
							,"com.eazybytes.eazyschool.security"
							,"com.eazybytes.eazyschool.validations"})
@EnableJpaAuditing(auditorAwareRef="auditAwareImpl")
// we also included this Annotation to tell to SpringBoot framework that We enable the Spring Data JPA 
@EnableJpaRepositories("com.eazybytes.eazyschool.repository")
@EntityScan("com.eazybytes.eazyschool.model")
public class EazyschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazyschoolApplication.class, args);
	}

}
