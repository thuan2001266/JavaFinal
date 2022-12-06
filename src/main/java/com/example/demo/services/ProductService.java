package com.example.demo.services;

import com.example.demo.models.Product;

import java.util.Map;

public interface ProductService {
    boolean addProduct(Product product);
    boolean deleteProduct(Long id);
    boolean updateProduct(Map<String, String> body);
}
