package com.example.demo.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @ManyToOne(fetch = FetchType.EAGER)
    AppUser appUser;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "ProductOption", joinColumns = @JoinColumn(name = "receiptId"))
    private Set<ProductOption> addresses = new HashSet<>();

    String method;

    String mili;

    public Receipt() {
    }

    public Receipt(AppUser appUser, Set<ProductOption> addresses, String method, String mili) {
        this.appUser = appUser;
        this.addresses = addresses;
        this.method = method;
        this.mili = mili;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return appUser;
    }

    public void setUser(AppUser appUser) {
        this.appUser = appUser;
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

    public String getMili() {
        return mili;
    }

    public void setMili(String mili) {
        this.mili = mili;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", user=" + appUser +
                ", addresses=" + addresses +
                ", method='" + method + '\'' +
                ", mili=" + mili +
                '}';
    }
}
