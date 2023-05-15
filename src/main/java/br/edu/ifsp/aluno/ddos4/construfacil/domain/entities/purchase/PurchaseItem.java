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

    void increaseQuantityByOne() {
        quantity++;
    }

    void decreaseQuantityByOne() {
        quantity--;
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
