package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.purchase.Purchase;

import java.util.List;

public interface PurchaseDAO extends DAO<Purchase>{
    @Override
    void save(Purchase type);

    @Override
    void update(Purchase type);

    @Override
    Purchase serach(int id);
}
