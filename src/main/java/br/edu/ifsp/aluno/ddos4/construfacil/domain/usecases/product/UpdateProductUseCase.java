package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.ProductDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;

import java.util.Objects;

public class UpdateProductUseCase {
    private final ProductDAO productDAO;

    public UpdateProductUseCase(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void updateProduct(Product product){
        Objects.requireNonNull(product);

        ProductValidator validator = new ProductValidator();
        Notification notification = validator.validate(product);

        if (notification.hasMessages())
            throw new IllegalArgumentException(notification.getMessagesAsString());

        productDAO.findOneByKey(product.getId())
                    .orElseThrow(() -> new EntityNotFoundException("There is not such product!"));

        productDAO.update(product);
    }
}
