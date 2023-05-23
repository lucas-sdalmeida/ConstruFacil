package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Purchase {
    private Long id;
    private final LocalDate date;
    private final Supplier supplier;
    private final Map<Product, PurchaseItem> items = new HashMap<>();

    public Purchase(Long id, LocalDate date, Supplier supplier) {
        this.id = id;
        this.date = date;
        this.supplier = supplier;
    }

    public Purchase(LocalDate date, Supplier supplier) {
        this.date = date;
        this.supplier = supplier;
    }

    public Purchase(Supplier supplier) {
        this.date = LocalDate.now();
        this.supplier = supplier;
    }

    public double getTotalPrice() {
        return items.values()
                .stream()
                .mapToDouble(PurchaseItem::getTotalPrice)
                .sum();
    }

    public void addProduct(Product product, int amount, int actualPrice) {
        Objects.requireNonNull(product);

        if (hasProduct(product))
            throw new IllegalArgumentException("This product already has already been added!");
        if (amount <= 0)
            throw new IllegalArgumentException("The amount should be greater than zero!");
        if (actualPrice < 0)
            throw new IllegalArgumentException("The actual purchase price cannot be lower than zero!");

        PurchaseItem item = new PurchaseItem(product, amount, actualPrice);

        items.put(product, item);
    }

    public void increaseProductQuantityBy(Product product, int amount) {
        Objects.requireNonNull(product);

        if (!hasProduct(product))
            throw new IllegalArgumentException("This product has never been added!");

        items.get(product).increaseQuantityBy(amount);
    }

    public void decreaseProductQuantityBy(Product product, int amount) {
        Objects.requireNonNull(product);

        if (!hasProduct(product))
            throw new IllegalArgumentException("This product has never been added!");

        PurchaseItem item = items.get(product);

        if (item.getQuantity() - amount <= 0) {
            items.remove(product);
            return;
        }

        item.decreaseQuantityBy(amount);
    }

    public void removeProduct(Product product) {
        Objects.requireNonNull(product);

        items.remove(product);
    }

    public boolean hasProduct(Product product) {
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

    public Supplier getSupplier() {
        return supplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return date.equals(purchase.date) && supplier.equals(purchase.supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, supplier);
    }
}