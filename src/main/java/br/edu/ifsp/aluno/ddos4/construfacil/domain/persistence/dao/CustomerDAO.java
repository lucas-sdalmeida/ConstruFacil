package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;

public interface CustomerDAO extends DAO<Customer> {
    @Override
    void save(Customer customer);

    @Override
    void update(Customer customer);

    @Override
    Customer search(int id);
}
