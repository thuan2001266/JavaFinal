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
			userService.saveRole(new Role( "ROLE_USER"));
			userService.saveRole(new Role( "ROLE_ADMIN"));

			userService.saveUser(new User("admin", "admin@gmail.com", "123123123", new ArrayList<Role>()));
			userService.saveUser(new User("user", "email2@gmail.com", "123123123", new ArrayList<Role>()));

			userService.addRoleToUser("admin", "ROLE_ADMIN");
			userService.addRoleToUser("admin", "ROLE_USER");
			userService.addRoleToUser("user", "ROLE_USER");

			userService.enableUser("admin");
			userService.enableUser("user");
			//iphone
			productService.addProduct(new Product("iPhone 14", Arrays.asList("21.900.000","23.900.000","27.900.000"), Arrays.asList("Blue","Black"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png"), Arrays.asList("128GB","256GB", "512GB"), "", 0, "iphone","iPhone 14"));
			productService.addProduct(new Product("iPhone 14 Pro", Arrays.asList("23.900.000","25900000","29.900.000"), Arrays.asList("Blue","Black"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png"), Arrays.asList("128GB","256GB", "512GB"), "", 0, "iphone","iPhone 14"));
			productService.addProduct(new Product("iPhone 14 Pro Max", Arrays.asList("33.490.000","36.490.000"), Arrays.asList("Gold","Black"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/289700/s16/iPhone-14-Pro-series-%20(2)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/289700/s16/iPhone-14-Pro-series-%20(4)-650x650.png"), Arrays.asList("128GB","256GB"), "", 0, "iphone","iPhone 14"));
			productService.addProduct(new Product("iPhone 13", Arrays.asList("18.990.000","18.990.000"), Arrays.asList("Blue","White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png"), Arrays.asList("128GB","256GB", "512GB"), "", 0, "iphone","iPhone 14"));
			productService.addProduct(new Product("iPhone 13 Pro", Arrays.asList("23.900.000","25.900.000","29.900.000"), Arrays.asList("Blue","Black"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png"), Arrays.asList("128GB","256GB", "512GB"), "", 0, "iphone","iPhone 14"));
			productService.addProduct(new Product("iPhone 13 Pro Max", Arrays.asList("33.490.000","36.490.000"), Arrays.asList("Gold","Black"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/289700/s16/iPhone-14-Pro-series-%20(2)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/289700/s16/iPhone-14-Pro-series-%20(4)-650x650.png"), Arrays.asList("128GB","256GB"), "", 0, "iphone","iPhone 14"));
			//mac
			productService.addProduct(new Product("MacBook Air M2 2022 8-core GPU", Arrays.asList("38.990.000"), Arrays.asList("Dark Blue"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/44/289472/s16/apple-macbook-air-m2-2022-16gb-256gb-xanh-650x650.png"), Arrays.asList("RAM 16 GB - SSD 256 GB"), "", 0, "mac","Macbook Air"));
			productService.addProduct(new Product("MacBook Air M1 2021 8-core GPU", Arrays.asList("35.990.000"), Arrays.asList("Dark Blue"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/44/289472/s16/apple-macbook-air-m2-2022-16gb-256gb-xanh-650x650.png"), Arrays.asList("RAM 16 GB - SSD 256 GB"), "", 0, "mac","Macbook Air"));
			productService.addProduct(new Product("MacBook Pro M2 2022 8-core GPU", Arrays.asList("38.990.000"), Arrays.asList("Gray", "Silver"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/44/285958/s16/apple-macbook-pro-m2-2022-16gb-256gb-xa%CC%81m-650x650.png", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_75/https://cdn.tgdd.vn/Products/Images/44/285958/s16/apple-macbook-pro-m2-2022-16gb-256gb-xa%CC%81m-650x650.png"), Arrays.asList("RAM 16 GB - SSD 256 GB"), "", 0, "mac","Macbook Pro"));
			//ipad
			productService.addProduct(new Product("iPad Pro M2 12.9\" WiFi Cellular 128GB", Arrays.asList("35.990.000", "38.990.000"), Arrays.asList("Gray", "Silver"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/522/295468/s16/iPad-Pro-M2-129-5G-grey-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/522/295468/s16/iPad-Pro-M2-129-5G-sliver-thumb-650x650.png"), Arrays.asList("128GB","256GB"), "", 0, "ipad","iPad Pro"));
			productService.addProduct(new Product("iPad Pro M2 11 inch WiFi Cellular", Arrays.asList("27.490.000"), Arrays.asList("Gray"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/522/295458/s16/iPad-Pro-M2-11-5G-grey-thumb-650x650.png"), Arrays.asList("128GB"), "", 0, "ipad","iPad Pro"));
		};
	}
}