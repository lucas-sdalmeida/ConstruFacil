package br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.supplier;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.supplier.Supplier;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao.SupplierDAO;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.usecases.util.Validator;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class FindSupplierUseCase {
    private final SupplierDAO supplierDAO;

    public FindSupplierUseCase(SupplierDAO supplierDAO) {
        this.supplierDAO = supplierDAO;
    }

    public Optional<Supplier> findOneById(Long id) {
        Objects.requireNonNull(id);

        return supplierDAO.findOneByKey(id);
    }

    public Optional<Supplier> findOneByCNPJ(String cnpj) {
        if (Validator.isNullOrEmpty(cnpj))
            throw new IllegalArgumentException("CNPJ is required for this operation!");

        return supplierDAO.findOneByCNPJ(cnpj);
    }

    public Map<Long, Supplier> findAll() {
        return supplierDAO.findAll();
    }
}
