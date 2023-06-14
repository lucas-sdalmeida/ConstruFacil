package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.inmemory;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SaleDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemorySaleDAO implements SaleDAO {
    private static final Map<Long, Sale> sales = new HashMap<>();
    private static long idGenerator = 0;

    @Override
    public void save(Sale sale) {
        sales.put(++idGenerator, sale);
    }

    @Override
    public void update(Sale sale) {
        sales.put(sale.getId(), sale);
    }

    @Override
    public Optional<Sale> findOneByKey(Long key) {
        return Optional.ofNullable(sales.get(key));
    }

    @Override
    public Optional<Sale> findOneByCustomerAndDate(Customer customer, LocalDateTime date) {
        return sales.values().stream()
                .filter(s -> s.getCustomer().equals(customer) && s.getIssueDate().equals(date))
                .findFirst();
    }

    @Override
    public Map<Long, Sale> findSomeByPeriod(LocalDateTime start, LocalDateTime end) {
        return sales.values()
                .stream()
                .filter(sale -> isInPeriod(sale.getIssueDate(), start, end))
                .collect(Collectors.toMap(Sale::getId, sale -> sale));
    }

    private boolean isInPeriod(LocalDateTime dateTime, LocalDateTime start, LocalDateTime end) {
        return (dateTime.equals(start) || dateTime.isAfter(start)) && (dateTime.equals(end) || dateTime.isBefore(end));
    }

    @Override
    public Map<Long, Sale> findSomeByCustomer(Customer customer) {
        return sales.values()
                .stream()
                .filter(sale -> sale.getCustomer().equals(customer))
                .collect(Collectors.toMap(Sale::getId, sale -> sale));
    }

    @Override
    public Map<Long, Sale> findSomeByProduct(Product product) {
        return sales.values()
                .stream()
                .filter(sale -> sale.hasProduct(product))
                .collect(Collectors.toMap(Sale::getId, sale -> sale));
    }

    @Override
    public Map<Long, Sale> findAll() {
        return Map.copyOf(sales);
    }

    @Override
    public void deleteByKey(Long key) {
        sales.remove(key);
    }

    @Override
    public Sale resultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public long getTotalIncoming() {
        return sales.values()
                .stream()
                .mapToLong(Sale::getTotalPriceInCents)
                .sum();
    }
}
