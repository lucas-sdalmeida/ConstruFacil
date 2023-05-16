package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import br.edu.ifsp.aluno.ddos4.construfacil.domain.entities.stock.Stock;
import br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util.DAO;

public interface StockDAO extends DAO<Stock> {
    @Override
    void save(Stock stock);

    @Override
    void update(Stock stock);

    @Override
    Stock search(int id);
}
