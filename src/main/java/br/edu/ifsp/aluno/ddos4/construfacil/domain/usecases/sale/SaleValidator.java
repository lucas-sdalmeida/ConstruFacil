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

        if (sale.getCustomer() == null)
            notification.addMessage("A customer must be assigned to this sale!");
        if (!sale.hasSaleItems())
            notification.addMessage("A sale without items is useless at all!");

        return notification;
    }
}
