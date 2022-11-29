package com.example.demo.controllers;

import com.example.demo.models.*;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private UserService userService;

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

//    @PostMapping("/login")
//    @CrossOrigin()
//    ReturnValue login(@RequestBody LoginInfo u) {
//        logger.info(u.toString());
//        User user = userService.getUser(u.getName());
//        if (user != null) {
//            return new ReturnValue(1, "success", "");
//        } else {
//            return new ReturnValue(0, "login fail", "");
//        }
//    }
}
