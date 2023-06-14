package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase.PurchaseItemDTO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase.PurchaseItemKey;

import java.util.Map;

public interface SupplierProvidesDAO extends DAO<PurchaseItemKey, PurchaseItemDTO> {
    Map<PurchaseItemKey, PurchaseItemDTO> findSomeByPurchase(Purchase purchase);

    long calculateAveragePurchasePriceByProductId(long productId);
}
