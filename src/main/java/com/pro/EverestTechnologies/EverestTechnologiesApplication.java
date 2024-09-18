package com.pro.EverestTechnologies;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Everest Technologies API",
				version = "1.0.0",
				description = "This is the documentation for the Everest Student Consultancy REST API",
				termsOfService = "https://www.everesttechnologies.com/terms",
				contact = @Contact(name = "Sai Chaitanya", email = "saichaitanya9989@gmail.com"),
				license = @License(name = "API License", url = "https://www.everesttechnologies.com/license")
		)
)


public class EverestTechnologiesApplication {
	public static void main(String[] args) {
		SpringApplication.run(EverestTechnologiesApplication.class, args);
	}
}
