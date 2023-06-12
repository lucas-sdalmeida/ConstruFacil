package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Validator;

import java.util.Objects;

public class PurchaseValidator extends Validator<Purchase> {
    @Override
    public Notification validate(Purchase purchase) {
        Objects.requireNonNull(purchase);

        Notification notification = new Notification();

        if (purchase.getSupplier() == null)
            notification.addMessage("A Supplier must be assigned to this purchase!");
        if (!purchase.hasItems())
            notification.addMessage("A purchase without items is useless at all!");

        return notification;
    }
}
