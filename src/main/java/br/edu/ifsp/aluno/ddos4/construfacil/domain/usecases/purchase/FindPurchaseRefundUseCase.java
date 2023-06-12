package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.PurchaseRefund;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.PurchaseRefundDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;

import java.util.Map;
import java.util.Optional;

public class FindPurchaseRefundUseCase {
    private final PurchaseRefundDAO purchaseRefundDAO;
    private final FindPurchaseUseCase findPurchaseUseCase;

    public FindPurchaseRefundUseCase(PurchaseRefundDAO purchaseRefundDAO, FindPurchaseUseCase findPurchaseUseCase) {
        this.purchaseRefundDAO = purchaseRefundDAO;
        this.findPurchaseUseCase = findPurchaseUseCase;
    }

    public Optional<PurchaseRefund> findOneById(long purchaseRefundId) {
        if (purchaseRefundId <= 0)
            throw new IllegalArgumentException("The id must be positive number!");

        return purchaseRefundDAO.findOneByKey(purchaseRefundId);
    }

    public Optional<PurchaseRefund> findOneByPurchaseId(long purchaseId) {
        Purchase purchase = findPurchaseUseCase.findOneById(purchaseId)
                                .orElseThrow(() -> new EntityNotFoundException("There is not such purchase!"));

        return purchaseRefundDAO.findOneByPurchase(purchase);
    }

    public Map<Long, PurchaseRefund> findAll() {
        return purchaseRefundDAO.findAll();
    }
}
