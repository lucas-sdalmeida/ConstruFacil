package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase;

import java.time.LocalDateTime;
import java.util.Objects;

public record PurchaseItemKey(Long supplierId, LocalDateTime issueDate, Long productId) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseItemKey that = (PurchaseItemKey) o;
        return Objects.equals(supplierId, that.supplierId) && Objects.equals(issueDate, that.issueDate) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, issueDate, productId);
    }
}
