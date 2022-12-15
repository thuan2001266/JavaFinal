package com.example.demo.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @ManyToOne(fetch = FetchType.EAGER)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    User user;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Product> products;

    public Receipt() {
    }

    public Receipt(User user, Set<Product> products) {
        this.user = user;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", users=" + user +
                ", products=" + products +
                '}';
    }
}
