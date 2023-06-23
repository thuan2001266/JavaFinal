package com.example.demo.repositories;


import com.example.demo.models.AppUser;
import com.example.demo.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
//    void deleteByUser(User user);

    void deleteAllByAppUser(AppUser tempAppUser);
}
