package com.example.authorizationserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class DefaultSecurityConfig {

	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		return http
			.authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.and()
			.build();
		// http.authorizeRequests(authorizeRequests ->
		// 		authorizeRequests.anyRequest().authenticated()
		// 	)

		// 	.formLogin(withDefaults());
		// return http.build();
	}

	@Bean
	UserDetailsService users() {

		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		UserDetails user = User.builder()
			.username("dabeeo")
			.password("king")
			.passwordEncoder(encoder::encode)
			.roles("USER")
			.build();

		// UserDetails user = User.withDefaultPasswordEncoder()
		// 	.username("sungsan")
		// 	.password("king")
		// 	.roles("USER")
		// 	.build();
		return new InMemoryUserDetailsManager(user);
	}
}
