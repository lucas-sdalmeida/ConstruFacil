package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

public interface SupplierProvidesDAO extends DAO<Long, Supplier>{
    long calculateAveragePurchasePriceByProductId(long productId);
}
