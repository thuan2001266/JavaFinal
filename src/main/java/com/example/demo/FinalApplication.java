package com.example.demo;

import com.example.demo.models.Product;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
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
			registry.addMapping("/login");
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
			productService.addProduct(new Product("iPhone 14", Arrays.asList("20.590.000","23.190.000","24.990.000"), Arrays.asList("Blue","Black", "White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/240259/s16/iPhone-14-thumb-topzone%20(2)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/289670/s16/iPhone-14-thumb-topzone%20(5)-650x650.png"), Arrays.asList("128GB","256GB", "512GB"), "", 0, "iphone","iPhone 14"));
			productService.addProduct(new Product("iPhone 14 Plus", Arrays.asList("23.090.000","25.690.000","27.990.000"), Arrays.asList("Blue","Purple"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/289712/s16/iPhone-14-plus-topzone%20(1)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/289712/s16/iPhone-14-plus-topzone%20(5)-650x650.png"), Arrays.asList("128GB","256GB", "512GB"), "", 0, "iphone","iPhone 14"));
			productService.addProduct(new Product("iPhone 14 Pro", Arrays.asList("28.090.000","28.690.000","33.990.000", "37.990.000"), Arrays.asList("Purple","Black", "Silver"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/289696/s16/iPhone-14-Pro-topzone%20(3)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/289696/s16/iPhone-14-Pro-topzone%20(1)-650x650.png","https://cdn.tgdd.vn/Products/Images/42/289696/s16/iPhone-14-Pro-topzone%20(2)-650x650.png"), Arrays.asList("128GB","256GB", "512GB", "1TB"), "", 0, "iphone","iPhone 14"));
			productService.addProduct(new Product("iPhone 14 Pro Max", Arrays.asList("30.090.000","32.690.000","35.990.000", "39.990.000"), Arrays.asList("Purple","Black"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/251192/s16/iPhone-14-Pro-series-%20(1)-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/251192/s16/iPhone-14-Pro-series-%20(4)-650x650.png"), Arrays.asList("128GB","256GB", "512GB", "1TB"), "", 0, "iphone","iPhone 14"));
			productService.addProduct(new Product("iPhone 13", Arrays.asList("18.590.000","20.690.000", "21.990.000"), Arrays.asList("Blue","Red"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/250257/s16/iphone-13-blue-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/250257/s16/iphone-13-red-650x650.png"), Arrays.asList("128GB","256GB", "512GB"), "", 0, "iphone","iPhone 13"));
			productService.addProduct(new Product("iPhone 13 Pro", Arrays.asList("25.900.000","27.900.000"), Arrays.asList("Blue","Gold"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/250726/s16/iphone-13-pro-blue-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/250726/s16/iphone-13-pro-gold-650x650.png"), Arrays.asList("256GB", "512GB"), "", 0, "iphone","iPhone 13"));
			productService.addProduct(new Product("iPhone 13 Pro Max", Arrays.asList("30.490.000","32.990.000"), Arrays.asList("Blue","Gray"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/250728/s16/iphone-13-pro-max-blue-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/250728/s16/iphone-13-pro-max-silver-650x650.png"), Arrays.asList("256GB", "512GB"), "", 0, "iphone","iPhone 13"));
			productService.addProduct(new Product("iPhone 12 Mini", Arrays.asList("13.990.000"), Arrays.asList("Red"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/228741/s16/iphone-12-mini-red-1-650x650.png"), Arrays.asList("64GB"), "", 0, "iphone","iPhone 12"));
			productService.addProduct(new Product("iPhone 12", Arrays.asList("15.690.000","17.990.000","18.990.000"), Arrays.asList("Green","Black", "Blue","White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/213031/s16/iphone-12-green-3-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/213031/s16/iphone-12-black-5-650x650.png","https://cdn.tgdd.vn/Products/Images/42/213031/s16/iphone-12-blue-3-650x650.png","https://cdn.tgdd.vn/Products/Images/42/213031/s16/iphone-12-white-3-650x650.png"), Arrays.asList("64GB","128GB","256GB"), "", 0, "iphone","iPhone 12"));
			productService.addProduct(new Product("iPhone SE (2022)", Arrays.asList("10.490.000","11.990.000"), Arrays.asList("White","Black"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/42/274158/s16/iphone-se-white-650x650.png", "https://cdn.tgdd.vn/Products/Images/42/274158/s16/iphone-se-black-650x650.png"), Arrays.asList("64GB","128GB"), "", 0, "iphone","iPhone SE"));
			//mac
			productService.addProduct(new Product("MacBook Air M2 2022", Arrays.asList("34.590.000","37.190.000"), Arrays.asList("Dark Blue","Gold"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/44/289472/s16/apple-macbook-air-m2-2022-16gb-256gb-xanh-650x650.png","https://cdn.tgdd.vn/Products/Images/44/289441/s16/apple-macbook-air-m2-2022-16gb-va%CC%80ng-650x650.png"), Arrays.asList("RAM 16 GB - SSD 256 GB","RAM 16 GB - SSD 512 GB"), "", 0, "mac","Macbook Air"));
			productService.addProduct(new Product("MacBook Pro 13 inch M2 2022", Arrays.asList("32.090.000","34.890.000","38.690.000"), Arrays.asList("Gray","Silver"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/44/285958/s16/apple-macbook-pro-m2-2022-16gb-256gb-xa%CC%81m-650x650.png","https://cdn.tgdd.vn/Products/Images/44/285958/s16/apple-macbook-pro-m2-2022-16gb-256gb-bac-1-2-650x650.png"), Arrays.asList("RAM 8 GB - SSD 256 GB","RAM 16 GB - SSD 256 GB","RAM 16 GB - SSD 512 GB"), "", 0, "mac","Macbook Pro"));
			productService.addProduct(new Product("MacBook Pro 14 inch M1 Pro 2021", Arrays.asList("42.790.000","49.990.000","64.400.000"), Arrays.asList("Gray","Silver"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/44/263914/s16/macbook-pro-14-m1-max-2021-10-core-cpu-xam-650x650.png","https://cdn.tgdd.vn/Products/Images/44/263914/s16/macbook-pro-14-m1-max-2021-10-core-cpu-bac-650x650.png"), Arrays.asList("RAM 16 GB - SSD 512 GB","RAM 16 GB - SSD 1 TB","RAM 32 GB - SSD 512 GB"), "", 0, "mac","Macbook Pro"));
			productService.addProduct(new Product("MacBook Pro 16 inch M1 Pro 2021", Arrays.asList("49.990.000","57.290.000","79.790.000"), Arrays.asList("Gray","Silver"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/44/253706/s16/macbook-pro-16-m1-pro-2021-xam-650x650.png","https://cdn.tgdd.vn/Products/Images/44/253706/s16/macbook-pro-16-m1-pro-2021-bac-650x650.png"), Arrays.asList("RAM 16 GB - SSD 512 GB","RAM 16 GB - SSD 1 TB","RAM 32 GB - SSD 1 TB"), "", 0, "mac","Macbook Pro"));
			productService.addProduct(new Product("MacBook Pro 13 inch M1 2020", Arrays.asList("30.290.000","31.690.000","45.590.000"), Arrays.asList("Gray","Silver"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/44/231255/s16/macbook-pro-13-spgry-m1-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/44/231255/s16/apple-macbook-pro-2020-z11c-thumb2-650x650.png"), Arrays.asList("RAM 16 GB - SSD 512 GB","RAM 16 GB - SSD 1 TB","RAM 32 GB - SSD 1 TB"), "", 0, "mac","Macbook Pro"));
			productService.addProduct(new Product("MacBook Air M1 2020", Arrays.asList("22.890.000","27.890.000"), Arrays.asList("Silver","Gray","Gold"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/44/231244/s16/macbook-air-m1-2020-silver-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/44/231244/s16/macbook-air-m1-2020-gold-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/44/231244/s16/macbook-air-m1-2020-gold-thumb-650x650.png"), Arrays.asList("RAM 8 GB - SSD 256 GB","RAM 16 GB - SSD 256 GB"), "", 0, "mac","Macbook Air"));
			productService.addProduct(new Product("iMac 24 inch M1 2021", Arrays.asList("29.990.000","33.090.000"), Arrays.asList("Blue","Silver","Pink"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/5698/238053/s16/imac-24inch-blue-650x650.png","https://cdn.tgdd.vn/Products/Images/5698/238053/s16/imac-24inch-silver-650x650.png","https://cdn.tgdd.vn/Products/Images/5698/238053/s16/imac-24inch-pink-650x650.png"), Arrays.asList("RAM 8 GB - SSD 256 GB","RAM 8 GB - SSD 256 GB"), "", 0, "mac","iMac"));
			productService.addProduct(new Product("Mac mini M1 2020", Arrays.asList("13.590.000","23.990.000"), Arrays.asList("Silver"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/5698/251248/s16/mac-mini-m1-2020-8gb-512gb-ab-thumb-1-650x650.png"), Arrays.asList("RAM 8 GB - SSD 256 GB","RAM 8 GB - SSD 256 GB"), "", 0, "mac","Mac mini"));
			//ipad
			productService.addProduct(new Product("iPad Pro M1 11 inch WiFi", Arrays.asList("20.490.000","23.490.000","35.990.000"), Arrays.asList("Gray","Silver"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/522/269327/s16/ipad-pro-m1-11-inch-wifi-gray-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/522/269327/s16/ipad-pro-m1-11-inch-wifi-silver-thumb-650x650.png"), Arrays.asList("128GB","256GB","1TB"), "", 0, "ipad","iPad Pro"));
			productService.addProduct(new Product("iPad Pro M1 12.9 inch WiFi + Cellular", Arrays.asList("29.290.000","31.390.000"), Arrays.asList("Gray","Silver"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/522/269327/s16/ipad-pro-m1-11-inch-wifi-gray-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/522/269327/s16/ipad-pro-m1-11-inch-wifi-silver-thumb-650x650.png"), Arrays.asList("128GB","256GB"), "", 0, "ipad","iPad Pro"));
			productService.addProduct(new Product("iPad Pro M1 12.9 inch WiFi", Arrays.asList("25.490.000","27.990.000","32.690.000"), Arrays.asList("Gray","Silver"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/522/269327/s16/ipad-pro-m1-11-inch-wifi-gray-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/522/269327/s16/ipad-pro-m1-11-inch-wifi-silver-thumb-650x650.png"), Arrays.asList("128GB","256GB","512GB"), "", 0, "ipad","iPad Pro"));
			productService.addProduct(new Product("iPad Air 5 WiFi", Arrays.asList("14.990.000"), Arrays.asList("White","Gray"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/522/248096/s16/ipad-air-5-wifi-startlight-650x650.png","https://cdn.tgdd.vn/Products/Images/522/248096/s16/ipad-air-5-m1-wifi-gray-thumb-650x650.png"), Arrays.asList("64GB"), "", 0, "ipad","iPad Air"));
			productService.addProduct(new Product("iPad Air 5 WiFi + Cellular", Arrays.asList("23.490.000"), Arrays.asList("Blue","White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/522/274156/s16/air-5-m1-wifi-cellular-xanh-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/522/248096/s16/ipad-air-5-wifi-startlight-650x650.png"), Arrays.asList("64GB"), "", 0, "ipad","iPad Air"));
			productService.addProduct(new Product("iPad mini 6 WiFi", Arrays.asList("12.690.000","15.990.000"), Arrays.asList("Pink","Gray"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/522/250734/s16/ipad-mini-6-5g-pink-650x650.png","https://cdn.tgdd.vn/Products/Images/522/250734/s16/ipad-mini-6-5g-gray-650x650.png"), Arrays.asList("64GB","256GB"), "", 0, "ipad","iPad Mini"));
			productService.addProduct(new Product("iPad Pro M2 12.9 inch WiFi Cellular", Arrays.asList("36.190.000","43.490.000"), Arrays.asList("Silver","Gray"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/522/295468/s16/iPad-Pro-M2-129-5G-sliver-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/522/295467/s16/iPad-Pro-M2-129-5G-grey-thumb-650x650.png"), Arrays.asList("256GB","512GB"), "", 0, "ipad","iPad Pro"));
			productService.addProduct(new Product("iPad Pro M2 12.9 inch WiFi", Arrays.asList("31.490.000","32.190.000"), Arrays.asList("Silver","Gray"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/522/295468/s16/iPad-Pro-M2-129-5G-sliver-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/522/295467/s16/iPad-Pro-M2-129-5G-grey-thumb-650x650.png"), Arrays.asList("256GB","512GB"), "", 0, "ipad","iPad Pro"));
			productService.addProduct(new Product("iPad Pro M2 11 inch WiFi", Arrays.asList("21.490.000","25.190.000"), Arrays.asList("Silver","Gray"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/522/295468/s16/iPad-Pro-M2-129-5G-sliver-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/522/295467/s16/iPad-Pro-M2-129-5G-grey-thumb-650x650.png"), Arrays.asList("256GB","512GB"), "", 0, "ipad","iPad Pro"));

			//watch
			productService.addProduct(new Product("Apple Watch Ultra GPS + Cellular 49mm Alpine Loop", Arrays.asList("19.990.000","19.990.000"), Arrays.asList("Orange","White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/7077/289817/s16/apple-watch-s8-ultra-size-l-cam-thumbtz-650x650.png","https://cdn.tgdd.vn/Products/Images/7077/289817/s16/apple-watch-s8-ultra-size-l-trang-kem-thumbtz-650x650.png"), Arrays.asList("size M" +"size L"), "", 0, "watch","Ultra GPS"));
			productService.addProduct(new Product("Apple Watch Ultra GPS + Cellular 49mm size M/L", Arrays.asList("20.990.000"), Arrays.asList("Black"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/7077/289823/s16/apple-watch-s8-ultra-trail-size-s-black-gray-thumbtz-650x650.png"), Arrays.asList(""), "", 0, "watch","Ultra GPS"));
			productService.addProduct(new Product("Apple Watch S8 GPS + Cellular 45mm", Arrays.asList("20.990.000"), Arrays.asList("Black"), Arrays.asList("https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/7077/289844/s16/apple-watch-s8-lte-45mm-day-thep-den-thumbtz-650x650.png"), Arrays.asList("45mm"), "", 0, "watch","S8 GPS"));
			productService.addProduct(new Product("Apple Watch S8 GPS + Cellular 41mm", Arrays.asList("18.990.000"), Arrays.asList("White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/7077/289832/s16/apple-watch-s8-lte-41mm-vien-thep-trang-thumbtz-650x650.png"), Arrays.asList("41mm"), "", 0, "watch","S8 GPS"));
			productService.addProduct(new Product("Apple Watch Series 8 GPS + Cellular 41mm", Arrays.asList("12.490.000","12.490.000"), Arrays.asList("White","Black","Red"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/7077/289806/s16/apple-watch-s8-lte-41mm-trang-kem-thumbttz-650x650.png","https://cdn.tgdd.vn/Products/Images/7077/289806/s16/apple-watch-s8-lte-41mm-den-xanh-thumbttz-650x650.png","https://cdn.tgdd.vn/Products/Images/7077/289806/s16/apple-watch-s8-lte-41mm-do-thumbtz-650x650.png"), Arrays.asList("41mm","45mm"), "", 0, "watch","Series 8"));

			//phukien
			productService.addProduct(new Product("Adapter sạc Apple Type C 67W", Arrays.asList("1.180.000"), Arrays.asList("White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/9499/287802/s16/adapter-sac-apple-type-c-67w-thumb-2-650x650.png"), Arrays.asList(""), "", 0, "accessory","Sạc"));
			productService.addProduct(new Product("Ốp lưng Magsafe cho iPhone 14 Pro Max Nhựa trong Apple MPU73", Arrays.asList("1.270.000"),Arrays.asList("White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/60/290326/s16/op-lung-magsafe-iphone-14-pro-max-nhua-trong-apple-mpu73-thumb-650x650.png"), Arrays.asList(""), "", 0, "accessory","Ốp lưng Magsafe"));
			productService.addProduct(new Product("Vỏ bảo vệ AirTag Apple Da Nâu", Arrays.asList("1.090.000"), Arrays.asList("Brown","Black","Blue"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/10618/257992/s16/moc-khoa-da-airtag-nau-1-650x650.png","https://cdn.tgdd.vn/Products/Images/10618/257992/s16/moc-khoa-da-airtag-%C4%91en-650x650.png","https://cdn.tgdd.vn/Products/Images/10618/257992/s16/moc-khoa-da-airtag-xanh-1-650x650.png"), Arrays.asList(""), "", 0, "accessory","AirTag"));
			productService.addProduct(new Product("Magic Keyboard với phím số", Arrays.asList("3.110.000"), Arrays.asList("Gray"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/1882/255340/s16/magic-keyboard-with-numeric-keypad-mq052-thumb-1-650x650.png"), Arrays.asList(""), "", 0, "accessory","Magic Keyboard"));
			productService.addProduct(new Product("Miếng dán kính iPhone 14 Pro UniQ", Arrays.asList("270.000"), Arrays.asList("White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/1363/291930/s16/mieng-dan-kinh-iphone-14-pro-uniq-thumb-650x650.png"), Arrays.asList(""), "", 0, "accessory","Miếng dán iPhone"));
			productService.addProduct(new Product("Apple Pencil (thế hệ 1)", Arrays.asList("2.600.000"), Arrays.asList("White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/1882/237414/s16/17-650x650.png"), Arrays.asList(""), "", 0, "accessory","Apple Pencil"));
			productService.addProduct(new Product("Magic Keyboard", Arrays.asList("2.230.000"), Arrays.asList("White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/1882/251881/s16/magic-keyboard-thumb-650x650.png"), Arrays.asList(""), "", 0, "accessory","Magic Keyboard"));
			productService.addProduct(new Product("Pin Dự Phòng MagSafe", Arrays.asList("2.540.000"), Arrays.asList("White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/57/266084/s16/pin-apple-magsafe-battery-pack-211221-090310-650x650.png"), Arrays.asList(""), "", 0, "accessory","Pin Dự Phòng"));
			productService.addProduct(new Product("Magic Mouse", Arrays.asList("2.240.000"), Arrays.asList("White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/86/251787/s16/1-650x650.png"), Arrays.asList(""), "", 0, "accessory","Magic Mouse"));
			productService.addProduct(new Product("Miếng dán camera iPhone 14 Pro | 14 Pro Max UniQ", Arrays.asList("380.000"), Arrays.asList("Gray"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/1363/291765/s16/mieng-dan-camera-iphone-14-pro-14-pro-max-uniq-bac-thumb-650x650.png"), Arrays.asList(""), "", 0, "accessory","Miếng dán iPhone"));
			productService.addProduct(new Product("Ốp lưng Magsafe cho iPhone 14 Pro Da Apple MPPK3", Arrays.asList("1.610.000"), Arrays.asList("Black","Brown"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/60/290324/s16/op-lung-magsafe-iphone-14-pro-da-apple-mppg3-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/60/290325/s16/op-lung-magsafe-iphone-14-pro-da-apple-mppk3-thumb-650x650.png"), Arrays.asList(""), "", 0, "accessory","Ốp lưng Magsafe"));
			productService.addProduct(new Product("Ốp lưng Magsafe cho iPhone 14 Pro Max Da Apple MPPM3", Arrays.asList("1.610.000"), Arrays.asList("Black","Brown"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/60/290327/s16/op-lung-magsafe-iphone-14-pro-max-da-apple-mppm3-thumb-650x650.png","https://cdn.tgdd.vn/Products/Images/60/290328/s16/op-lung-magsafe-iphone-14-pro-max-da-apple-mppq3-thumb-650x650.png"), Arrays.asList(""), "", 0, "accessory","Ốp lưng Magsafe"));
			productService.addProduct(new Product("Miếng dán kính iPhone 14 Pro Max Jcpal", Arrays.asList("400.000"), Arrays.asList("White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/1363/291825/s16/mieng-dan-kinh-iphone-14-pro-max-jcpal-thumb1-650x650.png"), Arrays.asList(""), "", 0, "accessory","Miếng dán iPhone"));
			productService.addProduct(new Product("Miếng dán kính iPhone 14 Pro Max Belkin", Arrays.asList("490.000"), Arrays.asList("White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/1363/291958/s16/mieng-dan-kinh-iphone-14-pro-max-belkin-thumb-1-650x650.png"), Arrays.asList(""), "", 0, "accessory","Miếng dán iPhone"));
			productService.addProduct(new Product("Cáp sạc USB-C 1m", Arrays.asList("530.000","530.000"), Arrays.asList("White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/58/203760/s16/122-650x650.png"), Arrays.asList(""), "", 0, "accessory","Cáp sạc USB-C"));
			productService.addProduct(new Product("AirTag", Arrays.asList("790.000"), Arrays.asList("White"), Arrays.asList("https://cdn.tgdd.vn/Products/Images/10618/238092/s16/airtag-650x650.png"), Arrays.asList(""), "", 0, "accessory","AirTag"));

		};
	}

}