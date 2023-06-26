package com.tatienduat.springboot.thymeafdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

	private static final String ADMIN = "ADMIN";
	private static final String MOD = "MOD";



	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}

	// beans
	// bcrypt bean definition
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		// Set permissions on endpoints
		http.authorizeHttpRequests((configurer) -> configurer
				// Our public endpoint
				.requestMatchers(HttpMethod.GET, "/user/**").permitAll()

				// Our private endpoints
				.requestMatchers("/department/**")
				.hasRole(ADMIN).requestMatchers("/user/**")
				.hasAnyRole(MOD, ADMIN)
				.anyRequest()
				.authenticated())
				.formLogin(form -> form.loginPage("/showMyLoginPage")
						.loginProcessingUrl("/authenticateTheUser")
						.permitAll())
				.logout(logout -> logout.permitAll())
				.exceptionHandling(configurer -> configurer
				.accessDeniedPage("/access-denied"));

		// use HTTP Basic authentication
		http.httpBasic();

		// disable Cross Site Request Forgery(CSRF)
		// in general, not required for stateless REST APIs that use POST, PUT, DELETE
		// and/or PATCH
		// Enable CORS and disable CSRF
		http = http.cors().and().csrf().disable();

		return http.build();
	}

}
