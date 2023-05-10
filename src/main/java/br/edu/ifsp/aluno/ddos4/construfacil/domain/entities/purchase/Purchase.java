package br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.product.Product;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.supplier;

import java.util.Date;
import java.util.List;

public class Purchase {
    private long id;
    private Date date;
    private supplier supplier;
    private List<Product> products;


    public Purchase(long id, Date date, br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.supplier supplier, List<Product> products) {
        this.id = id;
        this.date = date;
        this.supplier = supplier;
        this.products = products;
    }

    /*void public calculateTotal(){

    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.supplier supplier) {
        this.supplier = supplier;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}