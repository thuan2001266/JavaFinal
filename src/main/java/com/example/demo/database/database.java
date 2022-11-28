package com.example.demo.database;

import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class database {
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, UserRepository userRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
//                Product p1 = new Product("iPhone 13 Pro Max", "33999999", List.of("Black", "White", "Blue", "Red"),
//                        List.of("https://cdn.tgdd.vn/Products/Images/42/230529/s16/iphone-13-pro-max-gold-650x650.png",
//                                "https://cdn.tgdd.vn/Products/Images/42/230529/s16/iphone-13-pro-max-white-650x650.png",
//                                "https://cdn.tgdd.vn/Products/Images/42/230529/s16/iphone-13-pro-max-blue-650x650.png",
//                                "https://cdn.tgdd.vn/Products/Images/42/230529/s16/iphone-13-pro-max-red-650x650.png"),
//                        List.of("128GB",
//                                "256GB",
//                                "512GB"), "",20220911,"iphone", "iPhone 13");
//                Product p2 = new Product("iPhone 14 Pro Max", "37999999", List.of("Black", "White", "Blue", "Red"),
//                        List.of("https://cdn.tgdd.vn/Products/Images/42/230529/s16/iphone-13-pro-max-gold-650x650.png",
//                                "https://cdn.tgdd.vn/Products/Images/42/230529/s16/iphone-13-pro-max-white-650x650.png",
//                                "https://cdn.tgdd.vn/Products/Images/42/230529/s16/iphone-13-pro-max-blue-650x650.png",
//                                "https://cdn.tgdd.vn/Products/Images/42/230529/s16/iphone-13-pro-max-red-650x650.png"),
//                        List.of("128GB",
//                                "256GB",
//                                "512GB"), "", 20220911, "iphone", "iPhone 14");
//                System.out.println(productRepository.save(p1));
//                System.out.println(productRepository.save(p2));
//                userRepository.save(new AppUser("name","email","password"));

            }
        };
    }
}
