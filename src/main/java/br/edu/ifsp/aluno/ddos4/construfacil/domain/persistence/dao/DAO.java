package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.dao;

import java.util.List;

public interface DAO <T>{
    void save(T type);
    void update(T type);
    List<T> search(int id);
}
