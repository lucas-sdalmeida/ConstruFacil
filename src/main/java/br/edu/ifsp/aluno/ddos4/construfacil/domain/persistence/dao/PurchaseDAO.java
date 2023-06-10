package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public interface PurchaseDAO extends DAO<Long, Purchase> {
    Map<Long, Purchase> findSomeByDate(LocalDate date);

    Map<Long, Purchase> findSomeBySupplier(Supplier supplier);

    Map<Long, Purchase> findSomeBySupplierAndDate(Supplier supplier, LocalDate date);

    long getTotalPrice();
}
