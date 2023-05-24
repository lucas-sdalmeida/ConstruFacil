package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;

import java.util.Objects;

class SaleItem {
    private final Product product;
    private int quantity;
    private final double actualPrice;

    SaleItem(Product product, int quantity, double actualPrice) {
        this.product = product;
        this.quantity = quantity;
        this.actualPrice = actualPrice;
    }

    double getTotalPrice() {
        return actualPrice * quantity;
    }

    void increaseQuantityBy(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot increase the product quantity by a negative value!");
        quantity += amount;
    }

    void decreaseQuantityBy(int amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot decrease the quantity by a negative amount!");
        if (quantity - amount < 0) {
            throw new IllegalArgumentException(
                "Cannot decrease the quantity by " + amount +
                "because it would result in a negative quantity!"
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

    double getActualPrice() {
        return actualPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleItem saleItem = (SaleItem) o;
        return Double.compare(saleItem.actualPrice, actualPrice) == 0 && product.equals(saleItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, actualPrice);
    }
}
