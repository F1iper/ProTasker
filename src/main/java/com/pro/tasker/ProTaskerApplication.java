package com.pro.tasker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@SpringBootApplication
@ComponentScan
public class ProTaskerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProTaskerApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();

		UserDetails user = User.withUsername("user")
				.password("password")
				.authorities("USER_ROLE")
				.build();

		userDetailsManager.createUser(user);

		return userDetailsManager;
	}
}
