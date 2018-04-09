package com.progettoingegneria.service;

import com.progettoingegneria.dao.*;
import com.progettoingegneria.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Service per gestire la logica per sensori, sensori installati, posizioni, adattatori e valori
 */
@Service
public class SensoreService {
    @Autowired
    private ValoreDao valoreDao;

    @Autowired
    private SensoreDao sensoreDao;

    @Autowired
    private TraduttoreDao traduttoreDao;

    @Autowired
    private SensoreInstallatoDao sensoreInstallatoDao;

    @Autowired
    private PosizioneDao posizioneDao;

    @Autowired
    private SitoDao sitoDao;

    private static final Logger logger = LoggerFactory.getLogger(SensoreService.class);

    /**
     * Funzione per inserire nel db un valore ricevuto da un Sensore. Viene controllato
     * se l'id del sensore installato è presente nel DB, in caso affermativo viene recuperato
     * l'adattatore che usa e quindi parsato il messaggio. Infine viene inserito nel DB attraverso
     * il {@link com.progettoingegneria.dao.ValoreDao}
     * @param idSensoreInstallato id del sensore che invia il messaggio
     * @param stringaCifre messaggio codificato secondo le regole del sensore
     * @param messaggio stringa associata come messaggio
     */
    public void insertValore(Long idSensoreInstallato, String stringaCifre, String messaggio){

        //Controlliamo di avere un sensore_installato con questo id nel DB
        SensoreInstallato sensoreInstallato = sensoreInstallatoDao.findOne(idSensoreInstallato);

        if(sensoreInstallato != null) {
            Sensore sensore = sensoreDao.findOne(sensoreInstallato.getSensoreId());
            //recuperiamo il corrispondente traduttore
            Traduttore trad = traduttoreDao.findOne(sensore.getAdattatoreId());

            SimpleDateFormat sdfDate = new SimpleDateFormat(trad.getFormatoData());

            String split[] = stringaCifre.split(trad.getCarattereSplit());

            logger.info("Inserimento valore -> CODICE: " + split[trad.getPosizioneCodice()]);


            try {
                Valore v;
                if(!split[trad.getPosizioneCodice()].equals("0")) {
                    Timestamp dataAcquisizione = new Timestamp(sdfDate.parse(split[trad.getPosizioneData()]).getTime());
                    v = new Valore(sensoreInstallato.getSensoreInstallatoId(),
                            split[trad.getPosizioneValore()],
                            split[trad.getPosizioneCodice()],
                            stringaCifre,
                            messaggio,
                            dataAcquisizione);
                }
                else{
                    v = new Valore(sensoreInstallato.getSensoreInstallatoId(),
                            null,
                            split[trad.getPosizioneCodice()],
                            stringaCifre,
                            messaggio,
                            new Timestamp(System.currentTimeMillis()));
                }
                valoreDao.save(v);
            }
            catch (ParseException e){
                logger.info("Errore nel parsing data:"+split[trad.getPosizioneData()]);
            }

        }

    }

    /**
     * Utilizza il {@link com.progettoingegneria.dao.ValoreDao} per recuperare tutti i valori
     * di un sensore installato
     * @param id_sensore
     * @return una lista di oggetti della classe {@link com.progettoingegneria.entity.Valore}
     */
    public List<Valore> ricercaValorePerIdSensore(Long id_sensore){
        return valoreDao.findByIdSensoreInstallato(id_sensore);
    }

    /**
     * Inserisce un oggetto della classe {@link com.progettoingegneria.entity.Sensore} nel db
     * tramite {@link com.progettoingegneria.dao.SensoreDao}
     * @param s
     */
    public void insertSensoreDB(Sensore s){
        sensoreDao.save(s);
    }

    /**
     * Elimina un sensore dal Db a partire dal suo id e successivamente elimina anche
     * tutti i sensori installati di quel tipo di sensore
     * @param id Long
     */
    public void deleteSensoreDB(Long id){
        sensoreDao.delete(id);
        List<SensoreInstallato> listSensoreInstallato = sensoreInstallatoDao.findBySensoreId(id);
        for(SensoreInstallato si : listSensoreInstallato){
            deleteSensoreInstallatoDB(si.getSensoreInstallatoId());
        }
    }

    /**
     * Elimina un sito dal db e successivamente tutte le posizioni di quel sito
     * @param sitoId Long id del sito da eliminare
     */
    public void deleteSitoDB(Long sitoId){
        sitoDao.delete(sitoId);
        List<Posizione> listPosizione = posizioneDao.getPosizioniBySitoId(sitoId);
        for(Posizione si : listPosizione){
            deletePosizioneDB(si.getPosizioneId());
        }
    }

    /**
     * Elimina una posizione dal DB e tutti i sensori installati in quella posizione
     * @param posId Long id della posizione
     */
    public void deletePosizioneDB(Long posId){
        posizioneDao.delete(posId);
        sensoreInstallatoDao.deleteSensoreInstallatoByPosizioneId(posId);

    }

    /**
     * Restituisce tutti i sensori presenti in DB utilizzando il {@link com.progettoingegneria.dao.SensoreDao}
     * @return
     */
    public List<Sensore> getSensori(){
        return sensoreDao.findAll();
    }

    /**
     * Inserisce un oggetto della classe {@link com.progettoingegneria.entity.Traduttore} (adattatore) nel DB
     * @param trad
     */
    public void insertTraduttoreDB(Traduttore trad){ traduttoreDao.save(trad);}

    /**
     * Recupera tutti gli adattatori dal DB
     * @return una lista di oggetti della classe {@link com.progettoingegneria.entity.Traduttore}
     */
    public List<Traduttore> getTraduttori(){return traduttoreDao.findAll();}

    /**
     * Inserisce un oggetto della classe {@link com.progettoingegneria.entity.SensoreInstallato} nel DB.
     * Prima di inserirlo controlla che il sensore e la posizione siano presenti nel DB
     * @param s
     */
    public void insertSensoreInstallatoDB(SensoreInstallato s){
        Sensore sensoreCercato = sensoreDao.findOne(s.getSensoreId());
        Posizione posizioneCercata = posizioneDao.findOne(s.getPosizioneId());
        if(sensoreCercato != null && posizioneCercata != null) {
            sensoreInstallatoDao.save(s);
        }
    }

    /**
     * Elimina un sensore installato dal DB utilizzando il {@link com.progettoingegneria.dao.SensoreInstallatoDao}.
     * Succesivamente rimuove tutti i valori inseriti per quel determinato sensore installato
     * @param id Long id del sensore installato
     */
    public void deleteSensoreInstallatoDB(Long id){
        sensoreInstallatoDao.delete(id);
        valoreDao.deleteByIdSensoreInstallato(id);
    }

    /**
     * Restituisce tutti i sensori installati nel DB
     * @return una lista di oggetti della classe {@link com.progettoingegneria.entity.SensoreInstallato}
     */
    public List<SensoreInstallato> getSensoriInstallati(){return sensoreInstallatoDao.findAll();}

    /**
     * Recupera tutti i sensori installati in un sito.
     * @param idSito Long id del sito
     * @return lista di oggetti della classe {@link com.progettoingegneria.entity.SensoreInstallato}
     */
    public List<SensoreInstallato> getSensoriInstallatiPerSito(Long idSito){
        List<Posizione> listaPosizioni = posizioneDao.getPosizioniBySitoId(idSito);
        List<SensoreInstallato> listaSensoriInstallati = new ArrayList<SensoreInstallato>();

        for(Posizione pos : listaPosizioni){
            listaSensoriInstallati.addAll(sensoreInstallatoDao.findByPosizioneId(pos.getPosizioneId()));
        }

        return listaSensoriInstallati;
    }

    /**
     * Inserisce un oggetto della classe {@link com.progettoingegneria.entity.Posizione} nel DB.
     * Prima controlla che il sito della posizione sia presente nel DB, successivamente
     * esegue l'inserimento tramite {@link com.progettoingegneria.dao.PosizioneDao}
     * @param posizione
     */
    public void insertPosizioniDB(Posizione posizione){
        Sito sitoCercato = sitoDao.findOne(posizione.getSitoId());
        if(sitoCercato != null) {
            posizioneDao.save(posizione);
        }
    }

    /**
     * Restituisce il numero di valori con errore presenti nel DB
     * @return int numero di errori
     */
    public int numeroErroriRilevati(){
        return valoreDao.getNumeroErrori();
    }

    /**
     * Restituisce il numero di Siti presenti nel DB
     * @return int numero dei Siti
     */
    public int numeroSiti(){
        return sitoDao.getNumeroSiti();
    }

    /**
     * Restituisce tutti i siti presenti nel DB
     * @return lista di oggetti della classe {@link com.progettoingegneria.entity.Sito}
     */
    public List<Sito> listaSiti() { return sitoDao.findAll(); }

    /**
     * Restituisce il numero delle posizioni presenti nel DB
     * @return int numero delle posizioni
     */
    public int numeroPosizioni(){
        return posizioneDao.getNumeroPosizioni();
    }

    /**
     * Restituisce le posizioni per un determinato sito
     * @param sitoId Long id del sito
     * @return lista di oggetti della classe {@link com.progettoingegneria.entity.Posizione}
     */
    public List<Posizione> posizioniPerSito(Long sitoId){ return posizioneDao.getPosizioniBySitoId(sitoId);}

    /**
     * Restituisce il numero dei sensori installati
     * @return int numero dei sensori installati
     */
    public int numeroSensoriInstallati(){
        return sensoreInstallatoDao.getNumeroSensoriInstallati();
    }

    /**
     * Restituisce il numero dei valori ricevuti e inseriti nel DB
     * @return int numero dei valori
     */
    public int numeroValoriRicevuti(){
        return valoreDao.getNumeroValoriRicevuti();
    }

    /**
     * Restituisce i valori per un determinato Sito.
     * @param idSito Long id del sito
     * @return lista di oggetti della classe {@link com.progettoingegneria.entity.Valore}
     */
    public List<Valore> getValoriPerSito(Long idSito){
        //controllo se esiste il sito
        List<Valore> listValori = new ArrayList<Valore>();

        Sito sitoCercato = sitoDao.findOne(idSito);
        if(sitoCercato != null){
            //cerchiamo tutti i sensori installati in questo sito

            return valoreDao.getValoriPerSito(idSito);
        }
        else{
            return listValori;
        }
    }

    /**
     * Inserisce un nuovo Sito nel DB
     * @param sito oggetto della classe {@link com.progettoingegneria.entity.Sito}
     */
    public void insertSito(Sito sito){ sitoDao.save(sito); }




}
