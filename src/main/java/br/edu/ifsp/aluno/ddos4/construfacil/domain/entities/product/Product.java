package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product;

import java.util.Objects;

public class Product {
    private Long id;
    private final String name;
    private int quantity;
    private double defaultPurchasePrice;
    private double defaultSalePrice;

    public Product(Long id, String name, int quantity, double defaultPurchasePrice, double defaultSalePrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
