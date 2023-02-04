package com.example.demo.models;

import javax.persistence.Embeddable;

@Embeddable
public class ProductOption {
    int color;
    String id;
    int spec;
    int quant;

    public ProductOption() {
    }

    public ProductOption(int color, String id, int spec, int quant) {
        this.color = color;
        this.id = id;
        this.spec = spec;
        this.quant = quant;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSpec() {
        return spec;
    }

    public void setSpec(int spec) {
        this.spec = spec;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    @Override
    public String toString() {
        return "ProductOption{" +
                "color=" + color +
                ", id='" + id + '\'' +
                ", spec=" + spec +
                ", quant=" + quant +
                '}';
    }
}
