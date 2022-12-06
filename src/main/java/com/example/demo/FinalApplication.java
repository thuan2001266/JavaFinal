package com.example.demo;

import com.example.demo.models.Product;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;

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
			registry.addMapping("/api/product/cart");
		}
	}

	@Bean
	PasswordEncoder passwordEncoder (){
		return new BCryptPasswordEncoder();
	}
	@Bean
	CommandLineRunner run(UserService userService, ProductService productService) {
		return args -> {
//			userService.saveRole(new Role( "ROLE_USER"));
//			userService.saveRole(new Role( "ROLE_ADMIN"));
//
//			userService.saveUser(new User("Admin1", "Admin@gmail.com", "123123123", new ArrayList<Role>()));
//			userService.saveUser(new User("user2", "email2@gmail.com", "123456", new ArrayList<Role>()));
//			userService.saveUser(new User("user3", "email2@gmail.com", "123456", new ArrayList<Role>()));
//
//			userService.addRoleToUser("user1", "ROLE_ADMIN");
//			userService.addRoleToUser("user1", "ROLE_USER");
//			userService.addRoleToUser("user2", "ROLE_USER");
//			userService.addRoleToUser("user3", "ROLE_USER");

			productService.addProduct(new Product("iPhone 14", Arrays.asList("21900000","23900000","27900000"), Arrays.asList("Blue","Black"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png"), Arrays.asList("128GB","256GB", "512GB"), "", 0, "iphone","iPhone 14"));
			productService.addProduct(new Product("iPhone 14 Pro", Arrays.asList("23900000","25900000","29900000"), Arrays.asList("Blue","Black"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png"), Arrays.asList("128GB","256GB", "512GB"), "", 0, "iphone","iPhone 14"));

		};
	}
}