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
