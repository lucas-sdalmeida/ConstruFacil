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

        if (Validator.isNullOrEmpty(String.valueOf(purchase.getId())))
            notification.addMessage("Purchase's id is requeried!");
        if (Validator.isNullOrEmpty(String.valueOf(purchase.getDate())))
            notification.addMessage("Purchase's date is requeried!");

       return notification;
    }
}
