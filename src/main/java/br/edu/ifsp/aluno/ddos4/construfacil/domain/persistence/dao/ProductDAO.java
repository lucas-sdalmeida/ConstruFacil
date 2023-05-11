package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;

public interface ProductDAO extends DAO<Product>{
    @Override
    void save(Product product);

    @Override
    void update(Product product);

    @Override
    Product serach(int id);
}
