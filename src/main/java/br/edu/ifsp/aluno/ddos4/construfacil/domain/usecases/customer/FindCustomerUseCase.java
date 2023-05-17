package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.CustomerDAO;

import java.util.Map;
import java.util.Optional;

public class FindCustomerUseCase {
    private final CustomerDAO customerDAO;

    public FindCustomerUseCase(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Optional<Customer> findOneById(Long id) {
        return customerDAO.findOneByKey(id);
    }

    public Optional<Customer> findOneByCPF(String cpf) {
        return customerDAO.findOneByCPF(cpf);
    }

    public Map<Long, Customer> findAll() {
        return customerDAO.findAll();
    }
}
