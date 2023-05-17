package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.CustomerDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;

import java.util.Objects;

public class UpdateCustomerUseCase {
    private final CustomerDAO customerDAO;

    public UpdateCustomerUseCase(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void updateCustomer(Customer customer){
        Objects.requireNonNull(customer);

        CustomerValidator validator = new CustomerValidator();
        Notification notification = validator.validate(customer);

        customerDAO.findOneByKey(customer.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Customer not registered yet!"));

        customerDAO.update(customer);
    }
}
