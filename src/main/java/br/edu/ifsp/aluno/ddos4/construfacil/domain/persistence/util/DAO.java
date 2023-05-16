package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util;

import java.util.Map;
import java.util.Optional;

public interface DAO <K, T>{
    void save(T type);

    void update(T type);

    Optional<T> findOneByKey(K id);

    Map<K, T> findAll();
}
