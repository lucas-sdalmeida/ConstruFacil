package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.util.RegistrationStatus;

import java.util.Objects;

public class Product {
    private Long id;
    private final String name;
    private long stockQuantity;
    private Long averagePurchasePriceInCents;
    private RegistrationStatus status;

    public Product(Long id, String name, int stockQuantity, Long averagePurchasePriceInCents, RegistrationStatus status) {
        this.id = id;
        this.name = name;
        increaseStockQuantityBy(stockQuantity);
        setAveragePurchasePriceInCents(averagePurchasePriceInCents);
        this.status = status;
    }

    public Product(String name, int stockQuantity, Long averagePurchasePriceInCents) {
        this(null, name, stockQuantity, averagePurchasePriceInCents, RegistrationStatus.ACTIVE);
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

    public long getStockQuantity() {
        return stockQuantity;
    }

    public void increaseStockQuantityBy(long amount) {
        if (amount < 0)
            throw new IllegalArgumentException("The amount must be a non-negative number!");
        stockQuantity += amount;
    }

    public void decreaseStockQuantityBy(long amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("The amount must be a non-negative number!");
        stockQuantity -= amount;
    }

    public Long getAveragePurchasePriceInCents() {
        return averagePurchasePriceInCents;
    }

    public void setAveragePurchasePriceInCents(Long averagePurchasePriceInCents) {
        if (averagePurchasePriceInCents < 0)
            throw new IllegalArgumentException("The default purchase price cannot be lower than zero!");
        this.averagePurchasePriceInCents = averagePurchasePriceInCents;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
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
