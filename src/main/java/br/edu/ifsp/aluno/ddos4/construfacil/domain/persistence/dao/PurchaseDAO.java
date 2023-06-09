package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public interface PurchaseDAO extends DAO<Long, Purchase> {
    Optional<Purchase> findOneBySupplierAndDate(Supplier supplier, LocalDateTime date);

    Map<Long, Purchase> findSomeByPeriod(LocalDateTime start, LocalDateTime end);

    Map<Long, Purchase> findSomeBySupplier(Supplier supplier);

    Map<Long, Purchase> findSomeByProduct(Product product);

    long getTotalCost();

    long getAverageCostByProduct(Product product);
}
