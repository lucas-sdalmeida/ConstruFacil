package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;

import java.util.Objects;

public class SaleItem {
    private final Product product;
    private long priceInCents;

    public SaleItem(Product product, long priceInCents) {
        this.product = product;
        setPriceInCents(priceInCents);
    }

    public final Product getProduct() {
        return product;
    }

    public final Long getPriceInCents() {
        return priceInCents;
    }

    private void setPriceInCents(long priceInCents) {
        if (priceInCents < 0)
            throw new IllegalArgumentException("Price must be a non-negative number!");
        this.priceInCents = priceInCents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleItem that = (SaleItem) o;
        return product.equals(that.product) && priceInCents == that.priceInCents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, priceInCents);
    }
}
