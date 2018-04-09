package com.progettoingegneria.dao;

import com.progettoingegneria.entity.Sensore;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interfaccia DAO per i sensori
 */
public interface SensoreDao extends CrudRepository<Sensore,Long> {
    /**
     * Restituisce tutti i sensori presenti in DB
     * @return  una lista di oggetti di {@link com.progettoingegneria.entity.Sensore}
     */
    public List<Sensore> findAll();
}
