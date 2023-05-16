package br.edu.ifsp.aluno.ddos4.construfacil.application.repositories.sqlite.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.stock.Stock;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

import java.util.Map;
import java.util.Optional;

public class StockSQliteDAO implements DAO<Stock> {
    @Override
    public void save(Object type) {

    }

    @Override
    public void update(Object type) {

    }

    @Override
    public Optional findOneByKey(Object id) {
        return Optional.empty();
    }

    @Override
    public Map findAll() {
        return null;
    }
}
