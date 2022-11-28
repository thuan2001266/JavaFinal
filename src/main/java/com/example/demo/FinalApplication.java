package com.example.demo;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class FinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder (){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
//			userService.saveRole(new Role( "ROLE_USER"));
//			userService.saveRole(new Role( "ROLE_ADMIN"));
//
//			userService.saveUser(new User("user1", "email1@gmail.com", "123456", new ArrayList<Role>()));
//			userService.saveUser(new User("user2", "email2@gmail.com", "123456", new ArrayList<Role>()));
//			userService.saveUser(new User("user3", "email2@gmail.com", "123456", new ArrayList<Role>()));
//
//			userService.addRoleToUser("user1", "ROLE_ADMIN");
//			userService.addRoleToUser("user1", "ROLE_USER");
//			userService.addRoleToUser("user2", "ROLE_USER");
//			userService.addRoleToUser("user3", "ROLE_USER");
		};
	}
}
