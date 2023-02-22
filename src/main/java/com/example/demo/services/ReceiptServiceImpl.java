package com.example.demo.services;

import com.example.demo.controllers.ProductController;
import com.example.demo.models.OrderInfo;
import com.example.demo.models.Product;
import com.example.demo.models.ProductOption;
import com.example.demo.models.Receipt;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ReceiptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReceiptServiceImpl implements ReceiptService{

    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    final ReceiptRepository repository;

    @Autowired
    final ProductRepository productRepository;

    @Autowired
    final UserServiceImpl userService;

    public ReceiptServiceImpl(ReceiptRepository repository, ProductRepository productRepository, UserServiceImpl userService) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public List<Receipt> getAllReceipt(String name) {
        List<Receipt> receiptList= repository.findAll();
        List<Receipt> findReceipt = new ArrayList<>();
        if (receiptList.size()>0) {
            for (Receipt r : receiptList)
            {
                if (r.getUser().getName().equals(name)) {
                    findReceipt.add(r);
                }
            }
        }
        return findReceipt;
    }

    @Override
    public Receipt addReceipt(OrderInfo orderInfo) {
        Set<ProductOption> list = Arrays.stream(orderInfo.getList()).collect(Collectors.toSet());
        String user = orderInfo.getUser();
        String method = orderInfo.getMethod();
        DateFormat obj = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date res = new Date(new Date().getTime());
        return repository.save(new Receipt(userService.getUser(user), list, method,obj.format(res) ));
    }
}
