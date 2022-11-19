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

import java.time.LocalDate;
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

    @GetMapping("/product/type/{type}")
    ReturnValue getProductByType(@PathVariable String type) {
        List<Product> findByType = repository.findByType(type);
        if (findByType.size()>0) {
            return new ReturnValue(1, "success", findByType);
        } else {
            return new ReturnValue(0, "fail to get product by type "+type, "");
        }
    }

    @PostMapping("/addProduct")
    ReturnValue addProduct(@RequestBody Product product) {
        List<Product> products = repository.findByName(product.getName());
        if (products.size()>0) {
            return new ReturnValue(0, "Duplicated product name", "");
        }
        LocalDate tempdate = LocalDate.now();
        product.setDate(tempdate.getYear()*10000+tempdate.getMonthValue()*100+tempdate.getDayOfMonth());
        return new ReturnValue(1, "success", repository.save(product));
    }

    @PutMapping("/updateProduct/{id}")
    ReturnValue updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        ReturnValue rv = repository.findById(id).map(e -> {
            e.setName(newProduct.getName());
            e.setDiscount(newProduct.getDiscount());
            e.setImg(newProduct.getImg());
            e.setModel(newProduct.getModel());
            e.setOption(newProduct.getOption());
            e.setColor(newProduct.getColor());
            e.setPrice(newProduct.getPrice());
            e.setType(newProduct.getType());
            repository.save(e);
            return new ReturnValue(1, "success", e);
        }).orElseGet(() -> {
            Product nP = newProduct;
            nP.setId(id);
            return addProduct(newProduct);
        });
        return rv;
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