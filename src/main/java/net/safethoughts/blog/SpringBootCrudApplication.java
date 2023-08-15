package net.safethoughts.blog;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import net.safethoughts.blog.entity.Role;
import net.safethoughts.blog.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Role;


@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring Boot Blog App Rest API",
				description = "Spring Boot Blog App REST API Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Amit",
						email = "a.amitnandan@gmail.com",
						url = "http:....."
				),
				license = @License(
						name = "Apache 2.0",
						url = "http:...."
				)



		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Docs",
				url = "https://spring/doc"
		)
)
public class SpringBootCrudApplication implements CommandLineRunner {


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	} 

	public RoleRepository roleRepository;

	public SpringBootCrudApplication(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//
//		Role adminRole = new Role();
//		adminRole.setName("ROLE_ADMIN");
//		roleRepository.save(adminRole);
//
//		Role userRole = new Role();
//		userRole.setName("ROLE_USER");
//		roleRepository.save(userRole);
	}
}
