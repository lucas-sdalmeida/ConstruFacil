package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public interface SaleDAO extends DAO<Long, Sale> {
    Optional<Sale> findOneByCustomerAndDate(Customer customer, LocalDateTime date);

    Map<Long, Sale> findSomeByPeriod(LocalDateTime start, LocalDateTime end);

    Map<Long, Sale> findSomeByCustomer(Customer customer);

    Map<Long, Sale> findSomeByProduct(Product product);

    long getTotalIncoming();
}
