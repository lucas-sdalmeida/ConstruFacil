package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Sale {
    private Long id;
    private LocalDateTime issueDate;
    private Customer customer;
    private final Map<SaleItem, Long> saleItems = new HashMap<>();

    public Sale(Long id, LocalDateTime issueDate, Customer customer) {
        this.id = id;
        this.issueDate = issueDate;
        this.customer = customer;
    }

    public Sale() {
        this(null, null, null);
    }

    public Long getTotalPriceInCents() {
        return saleItems.keySet().stream()
                .mapToLong(item -> item.getPriceInCents() * saleItems.get(item))
                .sum();
    }

    public boolean hasProduct(Product product) {
        Objects.requireNonNull(product);

        return saleItems.keySet().stream()
                .anyMatch(item -> item.getProduct().equals(product));
    }

    public long getProductQuantity(Product product) {
        if (!hasProduct(product))
            return 0L;

        return saleItems.keySet().stream()
                .filter(item -> item.getProduct().equals(product))
                .mapToLong(this::getSaleItemQuantity)
                .sum();
    }

    public boolean hasSaleItem(SaleItem saleItem) {
        Objects.requireNonNull(saleItem);

        return saleItems.containsKey(saleItem);
    }

    public boolean hasSaleItems() {
        return !saleItems.isEmpty();
    }

    public List<SaleItem> getSaleItemsList() {
        return new ArrayList<>(saleItems.keySet());
    }

    public Long getSaleItemQuantity(SaleItem saleItem) {
        Objects.requireNonNull(saleItem);

        return saleItems.get(saleItem);
    }

    public void addSaleItem(SaleItem saleItem) {
        Objects.requireNonNull(saleItem);

        if (hasSaleItem(saleItem)) {
            increaseSaleItemQuantityBy(saleItem, 1L);
            return;
        }

        saleItems.put(saleItem, 1L);
    }

    public void removeSaleItem(SaleItem saleItem) {
        Objects.requireNonNull(saleItem);

        saleItems.remove(saleItem);
    }

    public void increaseSaleItemQuantityBy(SaleItem saleItem, long amount) {
        Objects.requireNonNull(saleItem);

        if (amount < 0)
            throw new IllegalArgumentException("The amount must be a non-negative number!");
        if (!hasSaleItem(saleItem))
            throw new IllegalArgumentException("There is not such item in this sale!");

        long currentQuantity = saleItems.get(saleItem);
        Product product = saleItem.getProduct();

        if (getProductQuantity(product) + amount > product.getQuantity())
            throw new IllegalArgumentException("Cannot increase the quantity by this value because " +
                    "there is not enough stock for this product!");

        saleItems.put(saleItem, currentQuantity + amount);
    }

    public void decreaseSaleItemQuantityBy(SaleItem saleItem, long amount) {
        Objects.requireNonNull(saleItem);

        if (amount < 0)
            throw new IllegalArgumentException("The amount must be a non-negative number!");
        if (!hasSaleItem(saleItem))
            throw new IllegalArgumentException("There is not such item in this purchase!");

        long currentQuantity = saleItems.get(saleItem);

        if (currentQuantity - amount <= 0) {
            removeSaleItem(saleItem);
            return;
        }

        saleItems.put(saleItem, currentQuantity - amount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sale sale = (Sale) o;
        return issueDate.equals(sale.issueDate) && customer.equals(sale.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueDate, customer);
    }
}
