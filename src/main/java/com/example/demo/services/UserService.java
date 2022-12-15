package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.Role;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    void enableUser(String username);
    User getByEmail(String email);

//    void deleteUserByName(String name);
//
//    void deleteUserByEmail(String name);

//    void deleteUserByEmail(String email);
}
