package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.util.Map;
import java.util.Optional;

public interface CustomerDAO extends DAO<Long, Customer> {
    Optional<Customer> findOneByCPF(String cpf);

    void inactivate(Customer customer);

    void activate(Customer customer);
}
