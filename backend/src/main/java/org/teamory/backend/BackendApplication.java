package org.teamory.backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@OpenAPIDefinition(
		info = @Info(
				title = "Teamory API",
				version = "1.0.0",
				description = "Comprehensive API documentation for Teamory backend",
				contact = @Contact(
						name = "Yassine from Teamory",
						email = "support@teamory.com",
						url = "https://teamory.com"
				),
				license = @License(
						name = "MIT License",
						url = "https://opensource.org/licenses/MIT"
				)
		)
)

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {
			System.out.println("Teamory say hello to all (^.^)");
		};
	}
}
