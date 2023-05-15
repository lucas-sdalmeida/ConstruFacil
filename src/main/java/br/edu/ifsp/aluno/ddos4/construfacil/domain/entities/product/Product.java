package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product;

import java.util.Objects;

public class Product {
    private long id;
    private String name;
    private double defaultPurchasePrice;
    private double defaultSalePrice;

    public Product(long id, String name, double defaultPurchasePrice, double defaultSalePrice) {
        this.id = id;
        this.name = name;
        this.defaultPurchasePrice = defaultPurchasePrice;
        this.defaultSalePrice = defaultSalePrice;
    }

    public Product(String name, double defaultPurchasePrice, double defaultSalePrice) {
        this.name = name;
        this.defaultPurchasePrice = defaultPurchasePrice;
        this.defaultSalePrice = defaultSalePrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDefaultPurchasePrice() {return defaultPurchasePrice;}

    public void setDefaultPurchasePrice(double defaultPurchasePrice) {
        this.defaultPurchasePrice = defaultPurchasePrice;
    }

    public double getDefaultSalePrice() {
        return defaultSalePrice;
    }

    public void setDefaultSalePrice(double defaultSalePrice) {
        this.defaultSalePrice = defaultSalePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
