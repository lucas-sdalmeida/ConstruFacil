package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.util.Map;
import java.util.Optional;

public interface ProductDAO extends DAO<Long, Product> {
    Optional<Product> findOneByName(String name);

    @Override
    void save(Product type);

    @Override
    void update(Product type);

    @Override
    Map<Long, Product> findAll();
}
