package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.util.Map;
import java.util.Optional;

public interface SupplierDAO extends DAO<Long, Supplier> {
    void save(Supplier supplier);

    void update(Supplier supplier);

    Optional<Supplier> findOneByKey(Long id);

    Optional<Supplier> findOneByCNPJ(String cnpj);

    Map<Long, Supplier> findAll();
}
