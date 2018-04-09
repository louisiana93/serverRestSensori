package com.progettoingegneria.dao;

import com.progettoingegneria.entity.Utente;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Interfaccia DAO per gli utenti
 */
public interface UtenteDao  extends CrudRepository<Utente,Long>{

    /**
     * Recupera tutti gli utenti presenti nel DB
     * @return lista di oggetti di {@link com.progettoingegneria.entity.Utente}
     */
    public List<Utente> findAll();

    /**
     * Ricera un utente con l'username
     * @param username
     * @return lista di oggetti di {@link com.progettoingegneria.entity.Utente}
     */
    public Utente findByUsername(String username);

    /**
     * Ricerca utente dall'id
     * @param utenteId
     * @return lista di oggetti di {@link com.progettoingegneria.entity.Utente}
     */
    public Utente findByUtenteId(Long utenteId);
}
