package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Validator;

import java.util.Objects;

public class ProductValidator extends Validator<Product> {

    @Override
    public Notification validate(Product product) {
        Objects.requireNonNull(product);

        Notification notification = new Notification();

        if(Validator.isNullOrEmpty(product.getName()))
            notification.addMessage("Product's name is required!");
        if (product.getStockQuantity() < 0)
            notification.addMessage("Product's quantity cannot be a negative value!");

        return notification;
    }
}
