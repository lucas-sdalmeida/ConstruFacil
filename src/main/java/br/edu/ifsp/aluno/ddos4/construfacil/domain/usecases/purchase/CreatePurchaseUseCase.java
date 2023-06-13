package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.PurchaseItem;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.PurchaseDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier.FindSupplierUseCase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;

import java.time.LocalDateTime;
import java.util.Objects;

public class CreatePurchaseUseCase {
    private final FindSupplierUseCase findSupplierUseCase;
    private final PurchaseDAO purchaseDAO;
    private Purchase purchase;

    private CreatePurchaseUseCase(FindSupplierUseCase findSupplierUseCase, PurchaseDAO purchaseDAO) {
        this.findSupplierUseCase = findSupplierUseCase;
        this.purchaseDAO = purchaseDAO;
    }

    public static CreatePurchaseUseCase beginPurchase(FindSupplierUseCase findSupplierUseCase,
                                                        PurchaseDAO purchaseDAO) {
        Objects.requireNonNull(findSupplierUseCase);
        Objects.requireNonNull(purchaseDAO);

        CreatePurchaseUseCase instance = new CreatePurchaseUseCase(findSupplierUseCase, purchaseDAO);
        instance.purchase = new Purchase();

        return instance;
    }

    public Long finishAndSavePurchase() {
        PurchaseValidator validator = new PurchaseValidator();
        Notification notification = validator.validate(purchase);

        if (notification.hasMessages())
            throw new IllegalStateException(notification.getMessagesAsString());

        if (purchase.getIssueDate() == null)
            purchase.setIssueDate(LocalDateTime.now());

        purchaseDAO.findOneBySupplierAndDate(purchase.getSupplier(), purchase.getIssueDate())
                .ifPresent(purchase -> {
                    throw new EntityAlreadyExistsException("This purchase has already been finished!");
                });

        purchaseDAO.save(purchase);
        long purchaseId = purchaseDAO.findOneBySupplierAndDate(purchase.getSupplier(), purchase.getIssueDate())
                                .orElseThrow(() -> new EntityNotFoundException("Could not save the purchase!"))
                                .getId();
        purchase.setId(purchaseId);
        updateProducts();

        return purchaseId;
    }

    public void assignSupplierById(long supplierId) {
        Supplier supplier = findSupplierUseCase.findOneById(supplierId)
                            .orElseThrow(() -> new EntityNotFoundException("There is not such supplier!"));

        purchase.setSupplier(supplier);
    }

    private void updateProducts() {
        purchase.getPurchasingItemsList()
                .stream()
                .map(PurchaseItem::getProduct)
                .distinct()
                .forEach(this::updateProduct);
    }

    private void updateProduct(Product product) {
        Objects.requireNonNull(product);

        product.increaseStockQuantityBy(purchase.getProductQuantity(product));
        product.setAveragePurchasePriceInCents(purchaseDAO.getAverageCostByProduct(product));
    }

    public void addPurchaseItem(PurchaseItem item) {
        purchase.addPurchaseItem(item);
    }

    public void increasePurchaseItemQuantityByOne(PurchaseItem item) {
        purchase.increasePurchaseItemQuantityBy(item, 1);
    }

    public void increasePurchaseItemQuantityBy(PurchaseItem item, long amount) {
        purchase.increasePurchaseItemQuantityBy(item, amount);
    }

    public void removePurchaseItem(PurchaseItem item) {
        purchase.removePurchaseItem(item);
    }

    public void decreasePurchaseItemQuantityByOne(PurchaseItem item) {
        purchase.decreasePurchaseItemQuantityBy(item, 1);
    }

    public void decreasePurchaseItemQuantityBy(PurchaseItem item, long amount) {
        purchase.decreasePurchaseItemQuantityBy(item, amount);
    }
}
