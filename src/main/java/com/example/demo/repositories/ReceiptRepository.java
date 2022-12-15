package com.example.demo.repositories;


import com.example.demo.models.Receipt;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
//    void deleteByUser(User user);

    void deleteAllByUser(User tempUser);
}
