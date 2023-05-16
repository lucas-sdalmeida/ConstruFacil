package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.ProductDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityAlreadyExistsException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.EntityNotFoundException;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Notification;

import java.util.Objects;

public class RegistryProductUseCase {
    private final ProductDAO productDAO;

    public RegistryProductUseCase(ProductDAO productDAO) {this.productDAO = productDAO;}

    public long registry(Product product){
        Objects.requireNonNull(product);

        ProductValidator validator = new ProductValidator();
        Notification notification = validator.validate(product);

        if (notification.hasMessages())
            throw new IllegalArgumentException(notification.getMessagesAsString());

        productDAO.findOneByName(product.getName())
                .ifPresent( s-> {
                    throw new EntityAlreadyExistsException(
                            "A product with name " + product.getName() +
                                    " has already been registred"
                    );
                });

        productDAO.save(product);
        long productId = productDAO
                .findOneByKey(product.getId())
                .orElseThrow(() ->
                            new EntityNotFoundException("The product has not been registred!")
                ).getId();

        return productId;
    }
}
