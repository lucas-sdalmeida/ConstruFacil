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

public class InMemorySaleDAO implements SaleDAO {
    private static final Map<Long, Sale> sales = new HashMap<>();

    @Override
    public Optional<Sale> findOneByCustomerAndDate(Customer customer, LocalDateTime date) {
        return Optional.empty();
    }

    @Override
    public Map<Long, Sale> findSomeByPeriod(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public Map<Long, Sale> findSomeByCustomer(Customer customer) {
        return null;
    }

    @Override
    public Map<Long, Sale> findSomeByProduct(Product product) {
        return null;
    }

    @Override
    public long getTotalIncoming() {
        return 0;
    }

    @Override
    public void save(Sale type) {

    }

    @Override
    public void update(Sale type) {

    }

    @Override
    public Optional<Sale> findOneByKey(Long key) {
        return Optional.empty();
    }

    @Override
    public Map<Long, Sale> findAll() {
        return null;
    }

    @Override
    public void deleteByKey(Long key) {

    }

    @Override
    public Sale resultSetToEntity(ResultSet resultSet) throws SQLException {
        return null;
    }
}
