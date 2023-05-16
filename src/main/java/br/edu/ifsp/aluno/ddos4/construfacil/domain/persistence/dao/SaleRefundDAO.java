package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.Sale;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.sale.SaleRefund;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.util.Map;
import java.util.Optional;

public interface SaleRefundDAO extends DAO<Long, SaleRefund> {
    @Override
    void save(SaleRefund type);

    @Override
    void update(SaleRefund type);

    Optional<SaleRefund> findOneBySale(Sale Sale);

    @Override
    Map<Long, SaleRefund> findAll();
}
