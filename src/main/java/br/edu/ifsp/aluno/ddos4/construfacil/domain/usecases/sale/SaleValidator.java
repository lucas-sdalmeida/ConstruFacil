package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.sale;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Validator;

import java.util.Objects;

public class SaleValidator extends Validator<Sale> {
    @Override
    public Notification validate(Sale sale) {
        Objects.requireNonNull(sale);

        Notification notification = new Notification();

        if (Validator.isNullOrEmpty(sale.getCustomer().getCpf()))
            notification.addMessage("Costumer's CPF is required!");

        return notification;
    }
}
