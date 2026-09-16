package com.mycompany.carpartsapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security{
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	
	    http
		.csrf(csrf -> csrf.disable())
	   	.authorizeHttpRequests(auth -> auth
			.anyRequest().authenticated()
	   	)
	   	.oauth2Login();

		return http.build();
	}
}
