package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.PurchaseRefund;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.PurchaseDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.PurchaseRefundDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier.FindSupplierUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.Objects;

public class CreatePurchaseRefundUseCase {
    private final FindPurchaseUseCase findPurchaseUseCase;
    private final PurchaseRefundDAO purchaseRefundDAO;

    public CreatePurchaseRefundUseCase(FindPurchaseUseCase findPurchaseUseCase, PurchaseRefundDAO purchaseRefundDAO) {
        this.findPurchaseUseCase = findPurchaseUseCase;
        this.purchaseRefundDAO = purchaseRefundDAO;
    }

    public void createRefund(long purchaseId) {
        Purchase purchase = findPurchaseUseCase.findOneById(purchaseId)
                                .orElseThrow(() -> new EntityNotFoundException("There is no purchase to this " +
                                            "supplier at this date and time!"));

        purchaseRefundDAO.findOneByPurchase(purchase)
                .ifPresent(refund -> {
                    throw new EntityAlreadyExistsException("There already is a refund for this purchase!");
                });

        PurchaseRefund refund = new PurchaseRefund(purchase);

        purchaseRefundDAO.save(refund);
    }
}
