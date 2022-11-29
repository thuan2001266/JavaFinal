package com.example.demo;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;

@SpringBootApplication
public class FinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalApplication.class, args);
	}


	@Configuration
	@EnableWebMvc
	public class WebConfig implements WebMvcConfigurer {

		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**");
		}
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



//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:3000");
//			}
//		};
//	}
//	@Bean
//	SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
//		http
//				// ...
//				.cors(cors -> cors.disable());
//		return http.build();
//	}