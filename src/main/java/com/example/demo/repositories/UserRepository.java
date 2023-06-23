package com.example.demo.repositories;

import com.example.demo.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByName(String name);
    AppUser findByEmail(String email);

    void deleteByName(String name);

    void deleteByEmail(String email);
}
