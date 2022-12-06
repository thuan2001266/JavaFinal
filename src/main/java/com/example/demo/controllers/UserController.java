package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.services.TokenService;
import com.example.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin()
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
    @CrossOrigin()
    ReturnValue register(@RequestParam Map<String, String> u) {
        User checkName = userService.getUser(u.get("name"));
        User checkEmail = userService.getByEmail(u.get("email"));
        String password = u.get("password");
        logger.info(u.get("name")+ " "+u.get("email"));
        if (checkName != null) {
            return new ReturnValue(0, "Username đã tồn tại", "");
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
        if (password.length()<7) {
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
        return new ReturnValue(1, "Tài khoản được đăng ký thành công", "");
    }

    @PostMapping("/verificate")
//    @CrossOrigin(origins = "http://localhost:3000")
    ReturnValue verificate(@RequestParam Map<String, String> body) {
        String token = body.get("token");
        logger.info(token);
        VerificationToken verificationToken = tokenService.findByToken(token);
//        logger.info(verificationToken.toString());
        if (verificationToken != null) {
            userService.enableUser(verificationToken.getUser().getName());
            return new ReturnValue(1, "Kích hoạt tài khoản thành công", "");
        } else {
            return new ReturnValue(0, "Token được sử dụng không chính xác", "");
        }
    }
}
