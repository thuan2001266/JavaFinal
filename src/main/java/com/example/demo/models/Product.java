package com.example.demo.models;

import javax.persistence.*;
import java.util.List;
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;
    String price;
    @Column
    @ElementCollection(targetClass=String.class)
    List<String> color;
    @Column
    @ElementCollection(targetClass=String.class)
    List<String> img;
    @Column
    @ElementCollection(targetClass=String.class)
    List<String> option;
    String discount;
    int date;
    String type;
    String model;



    public Product() {
    }

    public Product(String name, String price, List<String> color, List<String> img, List<String> option, String discount, int date, String type, String model) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.img = img;
        this.option = option;
        this.discount = discount;
        this.date = date;
        this.type = type;
        this.model = model;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

    public List<String> getOption() {
        return option;
    }

    public void setOption(List<String> option) {
        this.option = option;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return id+"";
    }
}
