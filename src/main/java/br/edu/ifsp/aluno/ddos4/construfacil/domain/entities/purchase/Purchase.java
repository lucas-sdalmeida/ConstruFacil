package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Purchase {
    private long id;
    private final LocalDate date;
    private final Supplier supplier;
    private final Map<Product, PurchaseItem> items = new HashMap<>();

    public Purchase(long id, LocalDate date, Supplier supplier) {
        this.id = id;
        this.date = date;
        this.supplier = supplier;
    }

    public Purchase(LocalDate date, Supplier supplier) {
        this.date = date;
        this.supplier = supplier;
    }

    public double getTotalPrice() {
        return items.values()
                .stream()
                .mapToDouble(PurchaseItem::getTotalPrice)
                .sum();
    }

    public void addProduct(Product product, double actualPrice) {
        Objects.requireNonNull(product);

        if (items.containsKey(product)) {
            items.get(product).increaseQuantityByOne();
            return;
        }

        items.put(product, new PurchaseItem(product, 1, actualPrice));
    }

    public void removeProduct(Product product) {
        Objects.requireNonNull(product);

        items.remove(product);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Supplier getSupplier() {
        return supplier;
    }
}