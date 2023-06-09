package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Sale {
    private Long id;
    private final LocalDate date;
    private final Customer customer;
    private final Map<Product, SaleItem> items = new HashMap<>();

    public Sale(Long id, LocalDate date, Customer customer) {
        this.id = id;
        this.date = date;
        this.customer = customer;
    }

    public Sale(LocalDate date, Customer customer) {
        this.date = date;
        this.customer = customer;
    }

    public Sale(Customer customer) {
        this.date = LocalDate.now();
        this.customer = customer;
    }

    public double getTotalPrice() {
        return items.values()
                .stream()
                .mapToDouble(SaleItem::getTotalPrice)
                .sum();
    }

    public void addProduct(Product product, int amount, double actualPrice) {
        Objects.requireNonNull(product);

        if (hasProduct(product))
            throw new IllegalArgumentException("This product has already been added!");
        if (amount <= 0)
            throw new IllegalArgumentException("Amount should be greater than zero!");
        if (actualPrice < product.getAvaragePurchasePrice()) {
            throw new IllegalArgumentException(
                "The actual sale price cannot be lower than the default purchase price!"
            );
        }

        SaleItem item = new SaleItem(product, amount, actualPrice);

        items.put(product, item);
    }

    public void increaseProductQuantityBy(Product product, int amount) {
        Objects.requireNonNull(product);

        if (!hasProduct(product))
            throw new IllegalArgumentException("This product has not been added yet!");

        items.get(product).increaseQuantityBy(amount);
    }

    public void decreaseProductQuantityBy(Product product, int amount) {
        Objects.requireNonNull(product);

        if (!hasProduct(product))
            throw new IllegalArgumentException("This product has not been added yet");

        SaleItem item = items.get(product);

        if (item.getQuantity() - amount <= 0) {
            removeProduct(product);
            return;
        }

        item.decreaseQuantityBy(amount);
    }

    public void removeProduct(Product product) {
        Objects.requireNonNull(product);

        items.remove(product);
    }

    public boolean hasProduct(Product product) {
        Objects.requireNonNull(product);

        return items.containsKey(product);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }
}
