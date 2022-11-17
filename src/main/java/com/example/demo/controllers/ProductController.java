package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.models.ReturnValue;
import com.example.demo.repositories.ProductRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping("/product")
    ReturnValue getAllProducts() {
        return new ReturnValue(1, "success", repository.findAll());
    }

    @GetMapping("/product/{id}")
    ReturnValue getProductById(@PathVariable Long id) {
        Optional<Product> findById = repository.findById(id);
        if (findById.isPresent()) {
            return new ReturnValue(1, "success", findById.stream().toList());
        } else {
            return new ReturnValue(0, "fail to get product by id "+id, "");
        }
    }

    @PostMapping("/addProduct")
    ReturnValue addProduct(@RequestBody Product product) {
        List<Product> products = repository.findByName(product.getName());
        if (products.size()>0) {
            return new ReturnValue(0, "Duplicated product name", "");
        }
        return new ReturnValue(1, "success", repository.save(product));
    }
}