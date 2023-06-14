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
        updateQuantityOfEachProduct(purchase);

        purchaseRefundDAO.save(refund);
        long refundId = purchaseRefundDAO.findOneByPurchase(purchase)
                            .orElseThrow(() -> new EntityNotFoundException("Could not save this refund!"))
                            .getId();
        refund.setId(refundId);
        updateAveragePurchasePriceOfEachProduct(purchase);

        return refundId;
    }

    private void updateAveragePurchasePriceOfEachProduct(Purchase purchase) {
        purchase.getPurchasingItemsList()
                .stream()
                .map(PurchaseItem::getProduct)
                .distinct()
                .forEach(this::updateProductAveragePurchasePrice);
    }

    private void updateProductAveragePurchasePrice(Product product) {
        product.setAveragePurchasePriceInCents(purchaseDAO.getAverageCostByProduct(product));
    }

    private void updateQuantityOfEachProduct(Purchase purchase) {
        purchase.getPurchasingItemsList()
                .stream()
                .map(PurchaseItem::getProduct)
                .distinct()
                .forEach(product -> product.decreaseStockQuantityBy(purchase.getProductQuantity(product)));
    }
}
