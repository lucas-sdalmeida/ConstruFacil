package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;

import java.util.List;

public interface ProductDAO extends DAO<Product>{
    @Override
    void save(Product product);

    @Override
    void update(Product product);

    @Override
    List<Product> search(int id);
}
