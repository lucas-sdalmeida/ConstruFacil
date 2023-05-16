package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;

public interface SupplierDAO extends DAO<Supplier> {
    @Override
    void save(Supplier supplier);

    @Override
    void update(Supplier supplier);

    @Override
    Supplier search(int id);
}
