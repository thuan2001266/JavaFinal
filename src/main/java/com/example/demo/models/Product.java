package com.example.demo.models;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "product")
public class Product {
    @Id
//    @SequenceGenerator(
//            name = "product_sequence",
//            sequenceName = "product_sequence",
//            allocationSize = 1
//    )
    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "product_sequence"
    )
    private Long id;
    String name;
    @Column(name = "price")
    @CollectionTable(name = "price", joinColumns = @JoinColumn(name = "productId"))
    @ElementCollection(targetClass=String.class)
    List<String> price;
    @Column(name = "color")
    @CollectionTable(name = "color", joinColumns = @JoinColumn(name = "productId"))
    @ElementCollection(targetClass=String.class)
    List<String> color;
    @Column(name = "img")
    @CollectionTable(name = "img", joinColumns = @JoinColumn(name = "productId"))
    @ElementCollection(targetClass=String.class)
    List<String> img;
    @Column(name = "optionToBuy")
    @CollectionTable(name = "optionToBuy", joinColumns = @JoinColumn(name = "productId"))
    @ElementCollection(targetClass=String.class)
    List<String> optionToBuy;
    String discount;
    long date;
    String type;
    String model;

    public Product() {
    }

    public Product(String name, List<String> price, List<String> color, List<String> img, List<String> optionToBuy, String discount, long date, String type, String model) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.img = img;
        this.optionToBuy = optionToBuy;
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

    public List<String> getPrice() {
        return price;
    }

    public void setPrice(List<String> price) {
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

    public List<String> getOptionToBuy() {
        return optionToBuy;
    }

    public void setOptionToBuy(List<String> optionToBuy) {
        this.optionToBuy = optionToBuy;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
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
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color=" + color +
                ", img=" + img +
                ", option=" + optionToBuy +
                ", discount='" + discount + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
