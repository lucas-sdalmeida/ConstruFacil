package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public final class Purchase {
    private Long id;
    private final LocalDateTime issueDate;
    private final Supplier supplier;
    private final Map<PurchaseItem, Long> purchasingItems = new HashMap<>();

    public Purchase(Long id, LocalDateTime issueDate, Supplier supplier) {
        this.id = id;
        this.issueDate = issueDate;
        this.supplier = supplier;
    }

    public Purchase(LocalDateTime issueDate, Supplier supplier) {
        this(null, issueDate, supplier);
    }

    public Long getTotalPriceInCents() {
        return purchasingItems.keySet().stream()
                .mapToLong(item -> item.getPriceInCents() * purchasingItems.get(item))
                .sum();
    }

    public boolean hasProduct(Product product) {
        Objects.requireNonNull(product);

        return purchasingItems.keySet().stream()
                .anyMatch(item -> item.getProduct().equals(product));
    }

    public boolean hasPurchaseItem(PurchaseItem purchaseItem) {
        Objects.requireNonNull(purchaseItem);

        return purchasingItems.containsKey(purchaseItem);
    }

    public List<PurchaseItem> getPurchasingItemsList() {
        return new ArrayList<>(purchasingItems.keySet());
    }

    public Long getPurchaseItemQuantity(PurchaseItem purchaseItem) {
        Objects.requireNonNull(purchaseItem);

        return purchasingItems.get(purchaseItem);
    }

    public void addPurchaseItem(PurchaseItem purchaseItem) {
        Objects.requireNonNull(purchaseItem);

        if (hasPurchaseItem(purchaseItem)) {
            increasePurchaseItemQuantityBy(purchaseItem, 1L);
            return;
        }

        purchasingItems.put(purchaseItem, 1L);
    }

    public void removePurchaseItem(PurchaseItem purchaseItem) {
        Objects.requireNonNull(purchaseItem);

        purchasingItems.remove(purchaseItem);
    }

    public void increasePurchaseItemQuantityBy(PurchaseItem purchaseItem, long amount) {
        Objects.requireNonNull(purchaseItem);

        if (amount < 0)
            throw new IllegalArgumentException("The amount must be a non-negative number!");
        if (!hasPurchaseItem(purchaseItem))
            throw new IllegalArgumentException("There is not such item in this purchase!");

        long currentQuantity = purchasingItems.get(purchaseItem);
        purchasingItems.put(purchaseItem, currentQuantity + amount);
    }

    public void decreasePurchaseItemQuantityBy(PurchaseItem purchaseItem, long amount) {
        Objects.requireNonNull(purchaseItem);

        if (amount < 0)
            throw new IllegalArgumentException("The amount must be a non-negative number!");
        if (!hasPurchaseItem(purchaseItem))
            throw new IllegalArgumentException("There is not such item in this purchase!");

        long currentQuantity = purchasingItems.get(purchaseItem);

        if (currentQuantity - amount <= 0) {
            removePurchaseItem(purchaseItem);
            return;
        }

        purchasingItems.put(purchaseItem, currentQuantity - amount);
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

    public Supplier getSupplier() {
        return supplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return issueDate.equals(purchase.issueDate) && supplier.equals(purchase.supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(issueDate, supplier);
    }
}