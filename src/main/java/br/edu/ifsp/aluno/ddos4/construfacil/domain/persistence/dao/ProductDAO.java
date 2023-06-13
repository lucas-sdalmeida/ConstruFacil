package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.util.Optional;

public interface ProductDAO extends DAO<Long, Product> {
    Optional<Product> findOneByName(String name);

    long getAveragePurchasePriceInCentsByProduct(Product product);

    void inactivate(Product product);

    void activate(Product product);
}
