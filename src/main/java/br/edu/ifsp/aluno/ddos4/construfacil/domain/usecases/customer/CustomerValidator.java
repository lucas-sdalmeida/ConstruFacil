package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.customer;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.customer.Customer;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Validator;

import java.util.Objects;

public class CustomerValidator extends Validator<Customer> {
    @Override
    public Notification validate(Customer customer) {
        Objects.requireNonNull(customer);

        Notification notification = new Notification();

        if (Validator.isNullOrEmpty(customer.getName()))
            notification.addMessage("Customer's name is required!");
        if (Validator.isNullOrEmpty(customer.getCpf()))
            notification.addMessage("Customer's CPF is required!");
        if (Validator.isNullOrEmpty(customer.getAddress()))
            notification.addMessage("Customer's address is required!");
        if (Validator.isNullOrEmpty(customer.getPhoneNumber()))
            notification.addMessage("Customer's phone number is required!");

        return notification;
    }
}
