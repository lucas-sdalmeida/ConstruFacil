package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;

import java.util.Map;
import java.util.Optional;

public class DAOSupplier implements SupplierDAO {

    @Override
    public void save(Supplier type) {

    }

    @Override
    public void update(Supplier type) {

    }

    @Override
    public Optional<Supplier> findOneByKey(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Supplier> findOneByCorporateName(String corporateName) {
        return Optional.empty();
    }

    @Override
    public Map<Long, Supplier> findAll() {
        return null;
    }
}
