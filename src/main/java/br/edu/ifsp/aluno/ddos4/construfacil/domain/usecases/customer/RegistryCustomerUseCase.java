package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.CustomerDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;

import java.util.Objects;

public class RegistryCustomerUseCase {
    private final CustomerDAO customerDAO;

    public RegistryCustomerUseCase(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public Long registry(Customer customer) {
        Objects.requireNonNull(customer);

        CustomerValidator validator = new CustomerValidator();
        Notification notification = validator.validate(customer);

        if (notification.hasMessages())
            throw new IllegalArgumentException(notification.getMessagesAsString());

        customerDAO.findOneByCPF(customer.getCpf())
                .ifPresent(c -> {
                    throw new EntityAlreadyExistsException(
                            "A customer with CPF '" + customer.getCpf() +
                            "' has already been registered."
                    );
                });

        customerDAO.save(customer);

        long customerId = customerDAO.findOneByCPF(customer.getCpf())
                                        .orElseThrow(
                                            () -> new EntityNotFoundException("" +
                                                "Couldn't find the new customer!"
                                            )
                                        ).getId();
        customer.setId(customerId);

        return customerId;
    }
}
