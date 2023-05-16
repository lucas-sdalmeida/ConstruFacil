package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public interface SaleDAO extends DAO<Long, Sale> {
    @Override
    void save(Sale type);

    @Override
    void update(Sale type);

    @Override
    Optional<Sale> findOneByKey(Long id);

    Map<Long, Sale> findSomeByDate(LocalDate date);

    Map<Long, Sale> findSomeByCustomer(Customer customer);

    Map<Long, Sale> findSomeByCustomerAndDate(Customer customer, LocalDate date);
}
