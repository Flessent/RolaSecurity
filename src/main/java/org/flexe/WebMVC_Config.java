package org.flexe;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Secured(value= {"ROLE_ADMIN"}) 

@CrossOrigin(origins = "http://localhost:4200")
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.flexe.controller"})
@RequestMapping("/RolaAPI/v1")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WebMVC_Config  implements WebMvcConfigurer  {
	private String testMessageBegrueßung;
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
	    }
	
	 @Override
	    public String toString() {
	        return String.format("HelloWorldBean [message=%s]", testMessageBegrueßung);
	    }
	
	
	 @Secured(value= {"ROLE_ADMIN"}) 

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(produces = "application/json")
	@RequestMapping("/greeting")
	public String greeting() {
		return "Hallo ! Bonjour";
	}
	
	
	

	}
	
	
	
	
	


