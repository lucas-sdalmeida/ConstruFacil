package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Validator;

import java.util.Objects;

public class SupplierValidator extends Validator<Supplier> {
    @Override
    public Notification validate(Supplier supplier) {
        Objects.requireNonNull(supplier);

        Notification notification = new Notification();

        if (Validator.isNullOrEmpty(supplier.getCorporateName()))
            notification.addMessage("Supplier's corporate name is required!");
        if (Validator.isNullOrEmpty(supplier.getCnpj()))
            notification.addMessage("Supplier's CNPJ is required!");
        if (Validator.isNullOrEmpty(supplier.getPhoneNumber()))
            notification.addMessage("Supplier's phone number is required!");

        return notification;
    }
}
