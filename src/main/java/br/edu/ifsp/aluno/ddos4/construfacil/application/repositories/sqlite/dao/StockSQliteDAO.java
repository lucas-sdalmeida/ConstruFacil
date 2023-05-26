package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.stock.Stock;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.util.Map;
import java.util.Optional;

public class StockSQliteDAO implements DAO<Long, Stock> {
    @Override
    public void save(Stock stock) {

    }

    @Override
    public void update(Stock stock) {

    }

    @Override
    public Optional<Stock> findOneByKey(Long id) {
        return Optional.empty();
    }

    @Override
    public Map<Long, Stock> findAll() {
        return null;
    }
}
