package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.Role;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    void enableUser(String username);
    User getByEmail(String email);
}
