package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.product;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.ProductDAO;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class FindProductUseCase {
    private final ProductDAO productDAO;

    public FindProductUseCase(ProductDAO productDAO) {this.productDAO = productDAO;}

    public Optional<Product> findOneBy(Long id){
        Objects.requireNonNull(id);

        return  productDAO.findOneByKey(id);
    }

    public Map<Long, Product> findAll(){return productDAO.findAll();}
}
