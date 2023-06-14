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
import java.util.stream.Collectors;

public class InMemoryPurchaseDAO implements PurchaseDAO {
    private static final Map<Long, Purchase> db = new HashMap<>();
    private static long idGenerator = 0;

    @Override
    public void save(Purchase purchase) {
        db.put(++idGenerator, purchase);
        purchase.setId(idGenerator);
    }

    @Override
    public void update(Purchase purchase) {
        db.put(purchase.getId(), purchase);
    }

    @Override
    public Optional<Purchase> findOneByKey(Long key) {
        return Optional.ofNullable(db.get(key));
    }

    @Override
    public Optional<Purchase> findOneBySupplierAndDate(Supplier supplier, LocalDateTime date) {
        return db.values()
                .stream()
                .filter(purchase -> purchase.getSupplier().equals(supplier) && purchase.getIssueDate().equals(date))
                .findFirst();
    }

    @Override
    public Map<Long, Purchase> findSomeByPeriod(LocalDateTime start, LocalDateTime end) {
        return db.values()
                .stream()
                .filter(purchase -> isInPeriod(purchase.getIssueDate(), start, end))
                .collect(Collectors.toMap(Purchase::getId, purchase -> purchase));
    }

    private boolean isInPeriod(LocalDateTime dateTime, LocalDateTime start, LocalDateTime end) {
        return (dateTime.equals(start) || dateTime.isAfter(start)) && (dateTime.equals(end) || dateTime.isBefore(end));
    }

    @Override
    public Map<Long, Purchase> findSomeBySupplier(Supplier supplier) {
        return db.values()
                .stream()
                .filter(purchase -> purchase.getSupplier().equals(supplier))
                .collect(Collectors.toMap(Purchase::getId, purchase -> purchase));
    }

    @Override
    public Map<Long, Purchase> findSomeByProduct(Product product) {
        return db.values()
                .stream()
                .filter(purchase -> purchase.hasProduct(product))
                .collect(Collectors.toMap(Purchase::getId, purchase -> purchase));
    }

    @Override
    public Map<Long, Purchase> findAll() {
        return Map.copyOf(db);
    }

    @Override
    public void deleteByKey(Long key) {
        db.remove(key);
    }

    @Override
    public long getTotalCost() {
        return db.values()
                .stream()
                .mapToLong(Purchase::getTotalPriceInCents)
                .sum();
    }

    @Override
    public long getAverageCostByProduct(Product product) {
        Long productTotalPrice = db.values()
                .stream()
                .filter(purchase -> purchase.hasProduct(product))
                .mapToLong(Purchase::getTotalPriceInCents)
                .sum();
        Long productQuantity = db.values()
                .stream()
                .filter(purchase -> purchase.hasProduct(product))
                .mapToLong(purchase -> purchase.getProductQuantity(product))
                .sum();

        return productTotalPrice / productQuantity;
    }

    @Override
    public Purchase resultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
