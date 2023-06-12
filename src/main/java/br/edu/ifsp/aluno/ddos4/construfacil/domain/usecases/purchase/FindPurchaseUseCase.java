package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.PurchaseDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product.FindProductUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier.FindSupplierUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class FindPurchaseUseCase {
    private final PurchaseDAO purchaseDAO;
    private final FindSupplierUseCase findSupplierUseCase;
    private final FindProductUseCase findProductUseCase;

    public FindPurchaseUseCase(PurchaseDAO purchaseDAO, FindSupplierUseCase findSupplierUseCase,
                               FindProductUseCase findProductUseCase) {
        this.purchaseDAO = purchaseDAO;
        this.findSupplierUseCase = findSupplierUseCase;
        this.findProductUseCase = findProductUseCase;
    }

    public Optional<Purchase> findOneById(long purchaseId) {
        if (purchaseId <= 0)
                throw new IllegalArgumentException("The id must be a positive number");

        return purchaseDAO.findOneByKey(purchaseId);
    }

    public Optional<Purchase> findOneBySupplierIdAndDateTime(long supplierId, LocalDateTime issueDate) {
        Objects.requireNonNull(issueDate);

        if (issueDate.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("It is impossible for a purchase to have been made after today!");

        Supplier supplier = findSupplierUseCase.findOneById(supplierId)
                                .orElseThrow(() -> new EntityNotFoundException("There is not such supplier!"));

        return purchaseDAO.findOneBySupplierAndDate(supplier, issueDate);
    }

    public Map<Long, Purchase> findSomeBySupplierId(long supplierId) {
        Supplier supplier = findSupplierUseCase.findOneById(supplierId)
                                .orElseThrow(() -> new EntityNotFoundException("There is not such supplier!"));

        return purchaseDAO.findSomeBySupplier(supplier);
    }

    public Map<Long, Purchase> findSomeByPeriod(LocalDateTime start, LocalDateTime end) {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);

        if (start.isAfter(end))
            throw new IllegalArgumentException("The start cannot be after the end!");

        return purchaseDAO.findSomeByPeriod(start, end);
    }

    public Map<Long, Purchase> findSomeByProductId(long productId) {
        Product product = findProductUseCase.findOneById(productId)
                                .orElseThrow(() -> new EntityNotFoundException("There is not such product!"));

        return purchaseDAO.findSomeByProduct(product);
    }

    public Map<Long, Purchase> findAll() {
        return purchaseDAO.findAll();
    }
}
