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

    public double getTotalPrice() {
        return items.values()
                .stream()
                .mapToDouble(SaleItem::getTotalPrice)
                .sum();
    }

    public void addProduct(Product product, double actualPrice) {
        Objects.requireNonNull(product);

        if (items.containsKey(product)) {
            items.get(product).increaseQuantityByOne();
            return;
        }

        items.put(product, new SaleItem(product, 1, actualPrice));
    }

    public void removeProduct(Product product) {
        Objects.requireNonNull(product);

        items.remove(product);
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
