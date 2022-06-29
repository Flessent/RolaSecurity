package org.flexe;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@Secured(value= {"ROLE_ADMIN"})
@RequestMapping("/RolaAPI/v1")

public class BasicAuthController implements WebMvcConfigurer {
	
	@Secured(value= {"ROLE_ADMIN"})
	 @GetMapping(path = "/basicRolaSecurity")
	    public WebMVC_Config basicauth() {
	        return new WebMVC_Config( "You are authenticated");
	    }



}