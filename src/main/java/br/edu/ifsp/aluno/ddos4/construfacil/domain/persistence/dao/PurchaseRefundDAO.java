package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.PurchaseRefund;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.util.Map;
import java.util.Optional;

public interface PurchaseRefundDAO extends DAO<Long, PurchaseRefund> {
    @Override
    void save(PurchaseRefund type);

    @Override
    void update(PurchaseRefund type);

    Optional<PurchaseRefund> findOneByPurchase(Purchase purchase);

    @Override
    Map<Long, PurchaseRefund> findAll();
}
