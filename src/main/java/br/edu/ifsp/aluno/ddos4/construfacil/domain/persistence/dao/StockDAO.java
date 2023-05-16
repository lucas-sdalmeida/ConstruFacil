package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.stock.Stock;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.util.Map;
import java.util.Optional;

public interface StockDAO extends DAO<Long, Stock> {
    @Override
    void save(Stock stock);

    @Override
    void update(Stock stock);

    @Override
    Optional<Stock> findOneByKey(Long id);

    @Override
    Map<Long, Stock> findAll();
}
