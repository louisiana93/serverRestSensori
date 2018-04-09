package com.progettoingegneria.dao;

import com.progettoingegneria.entity.Valore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Interfaccia Dao per i Valori
 */
public interface ValoreDao extends CrudRepository<Valore,Long> {
    //di default sta la save e la delete ereditate da CrudRepository

    /**
     * Restituisce tutti i valori filtrati per id del sensore installato
     * @param idSensoreInstallato
     * @return lista di oggetti di {@link com.progettoingegneria.entity.Valore}
     */
    public List<Valore> findByIdSensoreInstallato(Long idSensoreInstallato);

    /**
     * Elimina tutti i valori di un preciso id di un sensore installato
     * @param idsensore
     */
    @Transactional
    public void deleteByIdSensoreInstallato(Long idsensore);

    /**
     * Restituisce il numero di valori con Errore presenti nel DB
     * @return int
     */
    @Query("SELECT count(*) FROM Valore t where t.codiceAcquisito = 0")
    public int getNumeroErrori();

    /**
     * Restituisce il numero totale di valori ricevuti
     * @return
     */
    @Query("SELECT count(*) FROM Valore t")
    public int getNumeroValoriRicevuti();

    /**
     * Restituisce la lista di Valori filtrati per un determinato Sito
     * @param idSito
     * @return lista di oggetti di {@link com.progettoingegneria.entity.Valore}
     */
    @Query("SELECT v FROM Valore v, Sito s, Posizione p, SensoreInstallato si WHERE s.sitoId = :idSito and s.sitoId = p.sitoId and p.posizioneId = si.posizioneId and si.sensoreInstallatoId = v.idSensoreInstallato")
    public List<Valore> getValoriPerSito(@Param("idSito") Long idSito);
    }
