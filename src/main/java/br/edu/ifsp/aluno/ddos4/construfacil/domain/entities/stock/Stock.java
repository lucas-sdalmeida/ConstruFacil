package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.stock;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;

import java.util.List;

public class Stock {
    List<Product> products;

    public Stock(List<Product> products) {
        this.products = products;
    }

    public void storeProduct(Product product){
        products.add(product);
    }
}
