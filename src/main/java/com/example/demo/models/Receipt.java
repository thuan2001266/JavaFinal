package com.example.demo.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @ManyToOne(fetch = FetchType.EAGER)
    User user;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "ProductOption", joinColumns = @JoinColumn(name = "receiptId"))
    private Set<ProductOption> addresses = new HashSet<>();

    String method;

    public Receipt() {
    }

    public Receipt(User user, Set<ProductOption> addresses, String method) {
        this.user = user;
        this.addresses = addresses;
        this.method = method;
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

    public Set<ProductOption> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<ProductOption> addresses) {
        this.addresses = addresses;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", user=" + user +
                ", addresses=" + addresses +
                ", method='" + method + '\'' +
                '}';
    }
}
