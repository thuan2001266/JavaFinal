package com.example.demo.services;

import com.example.demo.controllers.ProductController;
import com.example.demo.models.Product;
import com.example.demo.models.Receipt;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ReceiptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        logger.info("Name trong impl: "+name);
        List<Receipt> receiptList= repository.findAll();
        logger.info(receiptList.toString());
        List<Receipt> findReceipt = new ArrayList<>();
        for (Receipt r : receiptList)
        {
            if (r.getUser().getName().equals(name)) {
                findReceipt.add(r);
            }
        }
        return findReceipt;
    }

    @Override
    public Receipt addReceipt(Map<String, String> body) {
        logger.info(body.get("list")+ " "+body.get("user")+ " "+body.get("method"));
        String[] pID = body.get("list").replace("[","").replace("]","").replace("\"","").split(",");
        Set<Product> productList = new HashSet<>();
        if (pID.length > 0) {
            for (String p : pID)
            {
                productList.add(productRepository.findById(Long.parseLong(p)).stream().toList().get(0));
            }
        }
        return repository.save(new Receipt(userService.getUser(body.get("user")), productList));
    }
}
