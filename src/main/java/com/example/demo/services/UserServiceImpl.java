package com.example.demo.services;

import com.example.demo.models.User;
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
    public User saveUser(User user) {
        if (userRepo.findByName(user.getName()) != null) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public void enableUser(String username) {
        User user = userRepo.findByName(username);
        user.setEnabled(true);
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
        User user = userRepo.findByName(username);
        Role role = roleRepo.findByName(roleName);
        if (!user.getRoles().contains(role)) {
            user.getRoles().add(role);
        }
    }

    @Override
    public User getUser(String username) {
        return userRepo.findByName(username);
    }

    @Override
    public User getByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }

//    @Override
//    public void deleteUserByName(String name) throws UsernameNotFoundException {
//        userRepo.deleteByName(name);
//    }
//
//    @Override
//    public void deleteUserByEmail(String email) {
//        User tempUser = userRepo.findByEmail(email);
//        receiptRepo.deleteAllByUser(tempUser);
//        userRepo.deleteByEmail(email);
//    }
}
