package br.edu.ifsp.aluno.ddos4.construfacil.domain.persistence.util;

import java.util.Map;
import java.util.Optional;

public interface DAO <K, T>{
    void save(T type);

    void update(T type);

    Optional<T> findOneByKey(K key);

    Map<K, T> findAll();

    void deleteByKey(K key);
}
