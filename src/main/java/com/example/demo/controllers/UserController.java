package com.example.demo.controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.models.*;
import com.example.demo.services.TokenService;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin("http://botzone.vercel.app")
public class UserController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/users")
    public ReturnValue getUsers() {
        return new ReturnValue(1, "success", userService.getUsers());
    }

    @PostMapping("/user/save")
    public ReturnValue saveUser(@RequestBody User user) {
        return new ReturnValue(1, "success", userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ReturnValue saveRole(@RequestBody Role role) {
        User user = userService.getUser("asd");
        return new ReturnValue(1, "success", userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ReturnValue giveUserRole(@RequestBody UsernameNRole usernameNRole) {
        userService.addRoleToUser(usernameNRole.getUsername(), usernameNRole.getRole());
        return new ReturnValue(1, "success", "");
    }

    @PostMapping("/register")
//    @CrossOrigin()
    ReturnValue register(@RequestParam Map<String, String> u) {
        User checkName = userService.getUser(u.get("name"));
        User checkEmail = userService.getByEmail(u.get("email"));
        String password = u.get("password");
        if (u.get("email") == null || u.get("email").equals("")) {
            return new ReturnValue(0, "Không được bỏ trống email", "");
        }
        if (checkEmail != null) {
            return new ReturnValue(0, "Email đã được sử dụng", "");
        }
        String[] checkValidEmail = u.get("email").split("@");
        if (checkValidEmail.length != 2) {
            return new ReturnValue(0, "Email không hợp lệ", "");
        } else {
            if (checkValidEmail[1].split(".").length == 1) {
                return new ReturnValue(0, "Email không hợp lệ", "");
            }
        }
        if (u.get("name") == null || u.get("name").equals("")) {
            return new ReturnValue(0, "Không được bỏ trống tài khoản", "");
        }
        if (u.get("name").length() < 6) {
            return new ReturnValue(0, "Tài khoản cần chứa nhiều hơn 6 ký tự", "");
        }
        if (checkName != null) {
            return new ReturnValue(0, "Username đã tồn tại", "");
        }
        if (u.get("password") == null || u.get("password").equals("")) {
            return new ReturnValue(0, "Không được bỏ trống password", "");
        }
        if (password.length()<8) {
            return new ReturnValue(0, "Mật khẩu cần có độ dài ít nhất 8 ký tự", "");
        }
        User user = userService.saveUser(new User(u.get("name"), u.get("email"), u.get("password"), new ArrayList<Role>()));
        userService.addRoleToUser(u.get("name"), "ROLE_USER");

        String tokenGenerate = UUID.randomUUID().toString();
//        VerificationToken verificationToken = new VerificationToken(tokenGenerate);
        VerificationToken verificationToken = tokenService.createToken(tokenGenerate, user);

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("vthuan26655@gmail.com");
        message.setTo(user.getEmail());
        message.setSubject("ACCOUNT VERIFICATION EMAIL");
        message.setText("Hello! This is your verification link: http://localhost:3000/verification?token=" + verificationToken.getToken());

        mailSender.send(message);
        return new ReturnValue(1, "Tài khoản được đăng ký thành công, vui lòng kiểm tra email và làm theo hướng dẫn để kích hoạt tài khoản", "");
    }

    @PostMapping("/verificate")
    ReturnValue verificate(@RequestParam Map<String, String> body) {
        String token = body.get("token");
        VerificationToken verificationToken = tokenService.findByToken(token);
        if (verificationToken != null) {
            userService.enableUser(verificationToken.getUser().getName());
            return new ReturnValue(1, "Kích hoạt tài khoản thành công", "");
        } else {
            return new ReturnValue(0, "Token được sử dụng không chính xác", "");
        }
    }

    @GetMapping("/refreshToken")
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Nauht ")){
            try {
                String refresh_token = authorizationHeader.substring(("Nauht ").length());
                Algorithm algorithm = Algorithm.HMAC256("100%secret".getBytes());
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create().withSubject(user.getName()).withExpiresAt(new Date(System.currentTimeMillis() +10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.joining(", ")))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
