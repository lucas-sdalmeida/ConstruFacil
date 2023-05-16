package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.util.Map;
import java.util.Optional;

public interface SupplierDAO extends DAO<Long, Supplier> {
    @Override
    void save(Supplier type);

    @Override
    void update(Supplier type);

    Optional<Supplier> findOneByCorporateName(String corporateName);

    @Override
    Map<Long, Supplier> findAll();
}
