package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.inmemory;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.PurchaseDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPurchaseDAO implements PurchaseDAO {
    private static final Map<Long, Purchase> db = new HashMap<>();

    @Override
    public Optional<Purchase> findOneBySupplierAndDate(Supplier supplier, LocalDateTime date) {
        return Optional.empty();
    }

    @Override
    public Map<Long, Purchase> findSomeByPeriod(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public Map<Long, Purchase> findSomeBySupplier(Supplier supplier) {
        return null;
    }

    @Override
    public Map<Long, Purchase> findSomeByProduct(Product product) {
        return null;
    }

    @Override
    public long getTotalCost() {
        return 0;
    }

    @Override
    public long getAverageCostByProduct(Product product) {
        return 0;
    }

    @Override
    public void save(Purchase type) {

    }

    @Override
    public void update(Purchase type) {

    }

    @Override
    public Optional<Purchase> findOneByKey(Long key) {
        return Optional.empty();
    }

    @Override
    public Map<Long, Purchase> findAll() {
        return null;
    }

    @Override
    public void deleteByKey(Long key) {

    }

    @Override
    public Purchase resultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
