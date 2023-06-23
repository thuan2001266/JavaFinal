package com.example.demo.services;

import com.example.demo.models.AppUser;
import com.example.demo.models.Role;

import java.util.List;

public interface UserService {
    AppUser saveUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getUser(String username);
    List<AppUser> getUsers();
    void enableUser(String username);
    AppUser getByEmail(String email);

//    void deleteUserByName(String name);
//
//    void deleteUserByEmail(String name);

//    void deleteUserByEmail(String email);
}
