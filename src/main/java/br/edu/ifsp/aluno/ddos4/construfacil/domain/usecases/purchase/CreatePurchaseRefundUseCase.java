package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.PurchaseItem;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.PurchaseRefund;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.PurchaseDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.PurchaseRefundDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;

public class CreatePurchaseRefundUseCase {
    private final FindPurchaseUseCase findPurchaseUseCase;
    private final PurchaseRefundDAO purchaseRefundDAO;
    private final PurchaseDAO purchaseDAO;

    public CreatePurchaseRefundUseCase(FindPurchaseUseCase findPurchaseUseCase, PurchaseRefundDAO purchaseRefundDAO,
                                       PurchaseDAO purchaseDAO) {
        this.findPurchaseUseCase = findPurchaseUseCase;
        this.purchaseRefundDAO = purchaseRefundDAO;
        this.purchaseDAO = purchaseDAO;
    }

    public long createRefund(long purchaseId) {
        Purchase purchase = findPurchaseUseCase.findOneById(purchaseId)
                                .orElseThrow(() -> new EntityNotFoundException("There is no purchase to this " +
                                            "supplier at this date and time!"));

        purchaseRefundDAO.findOneByPurchase(purchase)
                .ifPresent(refund -> {
                    throw new EntityAlreadyExistsException("There already is a refund for this purchase!");
                });

        PurchaseRefund refund = new PurchaseRefund(purchase);

        purchaseRefundDAO.save(refund);
        long refundId = purchaseRefundDAO.findOneByPurchase(purchase)
                            .orElseThrow(() -> new EntityNotFoundException("Could not save this refund!"))
                            .getId();
        refund.setId(refundId);
        updateProducts(purchase);

        return refundId;
    }

    private void updateProducts(Purchase purchase) {
        purchase.getPurchasingItemsList()
                .stream()
                .map(PurchaseItem::getProduct)
                .distinct()
                .forEach(product -> updateProduct(purchase, product));
    }

    private void updateProduct(Purchase purchase, Product product) {
        product.decreaseStockQuantityBy(purchase.getProductQuantity(product));
        product.setAveragePurchasePriceInCents(purchaseDAO.getAverageCostByProduct(product));
    }
}
