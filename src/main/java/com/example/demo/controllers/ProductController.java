package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.models.ReturnValue;
import com.example.demo.repositories.ProductRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/updateProduct/{id}")
    ReturnValue updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product product = repository.findById(id).map(e -> {
            e.setName(newProduct.getName());
            e.setDate(newProduct.getDate());
            e.setDiscount(newProduct.getDiscount());
            e.setImg(newProduct.getImg());
            e.setModel(newProduct.getModel());
            e.setOption(newProduct.getOption());
            e.setColor(newProduct.getColor());
            e.setPrice(newProduct.getPrice());
            e.setType(newProduct.getType());
            return repository.save(e);
        }).orElseGet(() -> {
            newProduct.setId(id);
            return repository.save(newProduct);
        });
        return new ReturnValue(1, "success", "");
    }

    @DeleteMapping("/deleteProduct/{id}")
    ReturnValue deleteProduct(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if(exists) {
            repository.deleteById(id);
            return new ReturnValue(1, "Deleted product by id "+ id, "");
        }
        return new ReturnValue(0, "Can not deleted product by id "+ id, "");
    }
}