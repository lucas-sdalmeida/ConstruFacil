package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;

import java.util.Objects;

class PurchaseItem {
    private final Product product;
    private int quantity;
    private final double actualPurchasePrice;

    PurchaseItem(Product product, int quantity, double actualPurchasePrice) {
        this.product = product;
        this.quantity = quantity;
        this.actualPurchasePrice = actualPurchasePrice;
    }

    double getTotalPrice() {
        return actualPurchasePrice * quantity;
    }

    void increaseQuantityBy(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot increase the product quantity by a negative value!");
        quantity += amount;
    }

    void decreaseQuantityBy(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot decrease the quantity by a negative value!");
        if (quantity - amount < 0) {
            throw new IllegalArgumentException(
                    "Cannot decrease product quantity by " + amount +
                            " because this would result in a negative quantity!"
            );
        }

        quantity -= amount;
    }

    Product getProduct() {
        return product;
    }

    int getQuantity() {
        return quantity;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    double getActualPurchasePrice() {
        return actualPurchasePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseItem that = (PurchaseItem) o;
        return Double.compare(that.actualPurchasePrice, actualPurchasePrice) == 0 && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, actualPurchasePrice);
    }
}
