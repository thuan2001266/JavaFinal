package com.example.demo.models;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

public class ReturnValue {
    int code;
    String message;
    List<Product> data;

    public ReturnValue(int code, String message, List<Product> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
