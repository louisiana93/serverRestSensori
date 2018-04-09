package com.progettoingegneria.dao;

import com.progettoingegneria.entity.Posizione;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interfaccia DAO per le Posizioni
 * Created by Luisiana D'Anzi on 10/15/17.
 */
public interface PosizioneDao extends CrudRepository<Posizione, Long>{

    /**
     * Restistuice il numero di posizioni
     * @return int
     */
    @Query("SELECT count(*) FROM Posizione t")
    public int getNumeroPosizioni();

    /**
     * Restituisce una lista di Posizioni per un determinato Sito
     * @param idSito id del sito di cui vogliamo le posizioni
     * @return lista di oggetti della classe {@link com.progettoingegneria.entity.Posizione}
     */
    public List<Posizione> getPosizioniBySitoId(Long idSito);

}
