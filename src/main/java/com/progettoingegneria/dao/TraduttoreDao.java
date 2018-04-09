package com.progettoingegneria.dao;


import com.progettoingegneria.entity.Traduttore;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interfaccia DAO per gli adattatori
 */
public interface TraduttoreDao extends CrudRepository<Traduttore, Long> {
    /**
     * Recupera tutti gli adattatori presenti in DB
     * @return lista di oggetti di {@link com.progettoingegneria.entity.Traduttore}
     */
    public List<Traduttore> findAll();
}
