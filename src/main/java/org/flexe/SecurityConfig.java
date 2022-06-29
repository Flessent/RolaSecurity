package org.flexe;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  { 
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
public String encoder(String passwd) { //encodage des mdp avec bcrypt
	BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
	return  bCryptPasswordEncoder.encode(passwd);
	
}
	
	@Bean
	public CorsFilter corsFilter() {
	    CorsConfiguration corsConfiguration = new CorsConfiguration();
	    corsConfiguration.setAllowCredentials(true);
	    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
	    corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
	            "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
	            "Access-Control-Request-Method", "Access-Control-Request-Headers"));
	    corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
	            "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
	    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
	    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
	    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
	    return new CorsFilter(urlBasedCorsConfigurationSource);
	}
		
	
	
    
	@Autowired
	public void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception{ 
		//auth.inMemoryAuthentication().withUser("admin").password(encoder("123")).roles("VERWALTER", "LEHRER");//identification en utilisant les users stockÃ©s en mÃ©moire
		auth.jdbcAuthentication()
	     .dataSource(dataSource)
	    // .passwordEncoder(passwordEncoder())//encodage en BCRYPT du mdp.TrÃ¨s important
	      .usersByUsernameQuery("select username as principal ,password as credentials ,true from users where username= ?")
	      .authoritiesByUsernameQuery("select ur.username as principal ,r.role as role  from  roles r inner join users_roles ur on ur.code_role=r.code_role where username=?" )
	      .rolePrefix("ROLE_");//ceci veut dire d'ajouter ROLE_ au role stockÃ© dans la BD.En effet, par defaut Spring l'ajoute au role
	
	
	}
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	            .withUser("flessent")
	            .password(encoder("12345"))
	            .roles("Gast","USER");
	    
	 }*/
	/*
	
	  @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  http.csrf().disable();
		  http.cors();
	        http
	            .authorizeHttpRequests((authz) -> authz
	                .anyRequest().authenticated()
	            )
	            .httpBasic();
	        return http.build();
	    }
	  */
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			//http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
		    http.csrf().disable();
		    http.cors();
		    http.httpBasic();
		    http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/login", "/**").permitAll();
		    http.authorizeRequests().antMatchers(HttpMethod.GET, "/", "/**").permitAll()

		            .anyRequest()
		            .authenticated()
		            .and()
		            .httpBasic();
		    
		}

}
