package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.PurchaseDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product.FindProductUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier.FindSupplierUseCase;

import java.time.LocalDateTime;
import java.util.List;
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

    public Optional<Purchase> findOneBySupplierIdAndDateTime(long supplierId, LocalDateTime issueDate) {
        return Optional.empty();
    }

    public List<Purchase> findSomeBySupplierId(long supplierId) {

    }

    public List<Purchase> findSomeByDateTime(LocalDateTime issueDate) {

    }

    public List<Purchase> findSomeByProductId(long productId) {

    }

    public List<Purchase> findAll() {
        
    }
}
