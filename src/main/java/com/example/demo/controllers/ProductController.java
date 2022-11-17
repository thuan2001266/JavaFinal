package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.models.ReturnValue;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/")
public class ProductController {
    @GetMapping("/ec")
    List<Product> getAllProducts() {
//        return List.of("Iphone", "Ipad");
//        ArrayList<Product> a = new ArrayList<Product>();
//        a.add();
//        a.add();
//        ReturnValue b = new ReturnValue(1, "Da select thanh cong toan bo san pham", List.of(new Product("p2", "456"), new Product("p3", "44456")));
        return List.of(new Product("p2", "456"), new Product("p3", "44456"));
    }
}

//localhost:8080/products
//
//        {
//            "code": "1",
//            "message": "Da select thanh cong toan bo san pham",
//            "data": {
//                { name: "p1", price: "21k"},
//                { name: "p2"},
//            }
//        }