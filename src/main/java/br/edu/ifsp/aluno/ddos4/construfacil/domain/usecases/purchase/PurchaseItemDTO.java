package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;

import java.util.Objects;

public record PurchaseItemDTO(PurchaseItemKey key, Product product, long priceInCents, long quantity) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseItemDTO that = (PurchaseItemDTO) o;
        return priceInCents == that.priceInCents && quantity == that.quantity && Objects.equals(key, that.key) &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, product, priceInCents, quantity);
    }
}
