package com.progettoingegneria.dao;

import com.progettoingegneria.entity.Sito;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Luisiana D'Anzi on 10/15/17.
 */
public interface SitoDao extends CrudRepository<Sito,Long>{

    /**
     * Recupera il numero dei Siti presenti in DB
     * @return int
     */
    @Query("SELECT count(*) FROM Sito t")
    public int getNumeroSiti();

    /**
     * Recupera tutti i siti presenti in DB
     * @return lista di oggetti di {@link com.progettoingegneria.entity.Sito}
     */
    public List<Sito> findAll();
}
