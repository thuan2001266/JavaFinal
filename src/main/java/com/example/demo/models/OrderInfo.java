package com.example.demo.models;

import java.util.Arrays;
import java.util.Objects;

public class OrderInfo {
    String user;
    String method;
    ProductOption[] list;

    public OrderInfo() {
    }

    public OrderInfo(String user, String method, ProductOption[] list) {
        this.user = user;
        this.method = method;
        this.list = list;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public ProductOption[] getList() {
        return list;
    }

    public void setList(ProductOption[] list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "user='" + user + '\'' +
                ", method='" + method + '\'' +
                ", list=" + Arrays.toString(list) +
                '}';
    }
}
