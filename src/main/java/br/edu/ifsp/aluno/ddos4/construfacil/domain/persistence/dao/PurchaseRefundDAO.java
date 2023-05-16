package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.PurchaseRefund;

import java.util.Optional;

public interface PurchaseRefundDAO {
    Optional<PurchaseRefund> findOneByPurchase(Purchase purchase);
}