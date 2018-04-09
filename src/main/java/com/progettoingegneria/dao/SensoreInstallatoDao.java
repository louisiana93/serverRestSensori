package com.progettoingegneria.dao;


import com.progettoingegneria.entity.Sensore;
import com.progettoingegneria.entity.SensoreInstallato;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interfaccia DAO per i sensori installati
 */
public interface SensoreInstallatoDao extends CrudRepository<SensoreInstallato,Long>{
    /**
     * Recupera un sensore installato a partire dal suo id
     * @param idSensore
     * @return lista di oggetti di {@link com.progettoingegneria.entity.SensoreInstallato}
     */
    public List<SensoreInstallato> findBySensoreId(Long idSensore);

    /**
     * Recupera il numero di sensori installati presenti in db
     * @return int
     */
    @Query("SELECT count(*) FROM SensoreInstallato t")
    public int getNumeroSensoriInstallati();

    /**
     * Recupera tutti i sensori installati presenti nel DB
     * @return lista di oggetti di {@link com.progettoingegneria.entity.SensoreInstallato}
     */
    public List<SensoreInstallato> findAll();

    /**
     * Recupera tutti i sensori installati filtrati per una posizione
     * @param posizioneId
     * @return lista di oggetti di {@link com.progettoingegneria.entity.SensoreInstallato}
     */
    public List<SensoreInstallato> findByPosizioneId(Long posizioneId);

    /**
     * Elimina un sensore installato dal DB
     * @param posizioneId
     */
    @Transactional
    public void deleteSensoreInstallatoByPosizioneId(Long posizioneId);
}
