package com.example.demo.services;

import com.example.demo.models.AppUser;
import com.example.demo.models.Role;
import com.example.demo.repositories.ReceiptRepository;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service @Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    final UserRepository userRepo;
    @Autowired
    final RoleRepository roleRepo;

    @Autowired
    final ReceiptRepository receiptRepo;

    @Autowired
    final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo, ReceiptRepository receiptRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.receiptRepo = receiptRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser saveUser(AppUser appUser) {
        if (userRepo.findByName(appUser.getName()) != null) {
            return null;
        }
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        return userRepo.save(appUser);
    }

    @Override
    public void enableUser(String username) {
        AppUser appUser = userRepo.findByName(username);
        appUser.setEnabled(true);
    }

    @Override
    public Role saveRole(Role role) {
        if (roleRepo.findByName(role.getName()) != null) {
            return null;
        }
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = userRepo.findByName(username);
        Role role = roleRepo.findByName(roleName);
        if (!appUser.getRoles().contains(role)) {
            appUser.getRoles().add(role);
        }
    }

    @Override
    public AppUser getUser(String username) {
        return userRepo.findByName(username);
    }

    @Override
    public AppUser getByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<AppUser> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepo.findByName(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(appUser.getName(), appUser.getPassword(), authorities);
    }
}
