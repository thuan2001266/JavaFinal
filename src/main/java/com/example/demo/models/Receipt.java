package com.example.demo.models;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @ManyToOne(fetch = FetchType.EAGER)
    User users;
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Product> products;

    public Receipt() {
    }

    public Receipt(User users, Set<Product> products) {
        this.users = users;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
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
                ", users=" + users +
                ", products=" + products +
                '}';
    }
}
