package com.progettoingegneria.controller;

import com.progettoingegneria.entity.*;
import com.progettoingegneria.service.SensoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Rest Controller per sensori, sensori installati, posizioni, adattatori e valori
 */
@RestController
@RequestMapping("/sensore")
public class SensoreController {

    @Autowired  //richiama un'altra classe (bean) quando serve senza scrivere codice
    private SensoreService sensoreService;

    /**
     * Metodo di test per controllare che il server sia avviato
     * @return
     */
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public String test(){
        return "Il sistema funziona";
    }

    //metodi Sito

    /**
     * Restituisce il numero di Siti nel DB
     * @return Integer Numero di Siti
     */
    @RequestMapping(value = "/getNumeroSiti", method = RequestMethod.GET)
    public int getNumeroSiti(){
        return sensoreService.numeroSiti();
    }

    /**
     * Restituisce la Lista di siti (con le loro caratteristiche) presenti in DB
     * @return
     */
    @RequestMapping(value = "/getSiti", method = RequestMethod.GET)
    public List<Sito> getListaSiti() { return sensoreService.listaSiti(); }

    /**
     * Inserisce o Aggiorna un sito in DB
     * @param sito Oggetto della Classe {@link com.progettoingegneria.entity.Sito}
     */
    @RequestMapping(value = "/insertSito", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertSito(@RequestBody Sito sito){
        sensoreService.insertSito(sito);
    }

    /**
     * Restituisce tutti i valori presenti in DB per un determinato Sito
     * @param idSito id del sito
     * @return lista di oggetti della classe {@link com.progettoingegneria.entity.Valore}
     */
    @RequestMapping(value = "/getValoriPerSito/{idSito}", method = RequestMethod.GET)
    public List<Valore> getValoriPerSito(@PathVariable("idSito") Long idSito){
        return sensoreService.getValoriPerSito(idSito);
    }

    /**
     * Elimina un Sito dal DB
     * @param idSito id del sito da eliminare
     */
    @RequestMapping(value = "/deleteSito/{id}", method = RequestMethod.GET)
    public void deleteSito(@PathVariable("id") Long idSito){ sensoreService.deleteSitoDB(idSito); }


    //metodi Posizioni

    /**
     * Inserisce o Aggiorna una Posizione nel DB
     * @param posizione Oggetto della classe {@link com.progettoingegneria.entity.Posizione}
     */
    @RequestMapping(value = "/insertPosizione", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertSensoreInstallato(@RequestBody Posizione posizione){
        sensoreService.insertPosizioniDB(posizione);
    }

    /**
     * Restituisce il numero delle posizioni prensenti in DB
     * @return int Il numero di posizioni
     */
    @RequestMapping(value = "/getNumeroPosizioni", method = RequestMethod.GET)
    public int getNumeroPosizioni(){
        return sensoreService.numeroPosizioni();
    }

    /**
     * Restituisce il numero di posizioni per un determinato sito
     * @param idSito
     * @return int Il numero di posizioni
     */
    @RequestMapping(value = "/getPosizioniPerSito/{idSito}", method = RequestMethod.GET)
    public List<Posizione> getPosizioniPerSito(@PathVariable("idSito") Long idSito){
        return sensoreService.posizioniPerSito(idSito);}

    /**
     * Elimina una posizione dal DB
     * @param idPosizione id della posizione da Eliminare
     */
    @RequestMapping(value = "/deletePosizione/{id}", method = RequestMethod.GET)
    public void deletePosizione(@PathVariable("id") Long idPosizione){ sensoreService.deletePosizioneDB(idPosizione); }



    //metodi Sensori

    /**
     * Inserisce o Aggiorna un sensore nel DB
     * @param sen oggetto della classe {@link com.progettoingegneria.entity.Sensore}
     */
    @RequestMapping(value = "/insertSensore", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertSensore(@RequestBody Sensore sen){ sensoreService.insertSensoreDB(sen);
    }

    /**
     * Elimina un sensore dal DB
     * @param idSensore id del sensore da eliminare
     */
    @RequestMapping(value = "/deleteSensore/{id}", method = RequestMethod.GET)
    public void deleteSensore(@PathVariable("id") Long idSensore){ sensoreService.deleteSensoreDB(idSensore); }

    /**
     * Restituisce tutti i sensori nel DB
     * @return lista di oggetti della classe {@link com.progettoingegneria.entity.Sensore}
     */
    @RequestMapping(value = "/getSensori", method = RequestMethod.GET)
    public List<Sensore> getSensori(){
        return sensoreService.getSensori();
    }



    //metodi sensori installati

    /**
     * Inserisce o Aggiorna un sensore installato nel DB
     * @param sensoreInstallato oggetto della classe {@link com.progettoingegneria.entity.SensoreInstallato}
     */
    @RequestMapping(value = "/insertSensoreInstallato", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertSensoreInstallato(@RequestBody SensoreInstallato sensoreInstallato){
        sensoreService.insertSensoreInstallatoDB(sensoreInstallato);
    }

    /**
     * Restituisce il numero di sensori Installati presenti nel DB
     * @return int
     */
    @RequestMapping(value = "/getNumeroSensoriInstallati", method = RequestMethod.GET)
    public int getNumeroSensoriInstallati(){
        return sensoreService.numeroSensoriInstallati();
    }

    /**
     * Elimina un sensore installato
     * @param idSensoreInstallato id del sensore da eliminare
     */
    @RequestMapping(value = "/deleteSensoreInstallato/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteSensoreInstallato(@PathVariable("id") Long idSensoreInstallato){
        sensoreService.deleteSensoreInstallatoDB(idSensoreInstallato);
    }

    /**
     * Restituisce una lista dei sensori installati presenti in DB
     * @return una lista di oggetti di {@link com.progettoingegneria.entity.SensoreInstallato}
     */
    @RequestMapping(value = "/getSensoriInstallati", method = RequestMethod.GET)
    public List<SensoreInstallato> getSensoriInstallati(){
        return sensoreService.getSensoriInstallati();
    }

    /**
     * Restituisce una lista di sensori installati per un determinato sito
     * @param idSito id del sito
     * @return una lista di oggetti di {@link com.progettoingegneria.entity.SensoreInstallato}
     */
    @RequestMapping(value = "/getSensoriInstallatiPerSito/{idSito}", method = RequestMethod.GET)
    public List<SensoreInstallato> getSensoriInstallatiPerSito(@PathVariable("idSito") Long idSito){
        return sensoreService.getSensoriInstallatiPerSito(idSito);
    }

    //metodi traduttore

    /**
     * Inserisce un nuovo adattatore nel db
     * @param traduttore oggetto della classe una lista di oggetti di {@link com.progettoingegneria.entity.Traduttore}
     */
    @RequestMapping(value = "/insertTraduttore", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertTraduttore(@RequestBody Traduttore traduttore){
        sensoreService.insertTraduttoreDB(traduttore);
    }

    /**
     * Restituisce tutti gli adattatori presenti in DB
     * @return una lista di oggetti della classe una lista di oggetti di {@link com.progettoingegneria.entity.Traduttore}
     */
    @RequestMapping(value = "/getTraduttori", method = RequestMethod.GET)
    public List<Traduttore> getTraduttori(){
        return sensoreService.getTraduttori();
    }


    //metodi valore

    /**
     * Restituisce il numero di valori ricevuti presenti nel DB
     * @return int
     */
    @RequestMapping(value = "/getNumeroValoriRicevuti", method = RequestMethod.GET)
    public int getNumeroValoriRicevuti(){
        return sensoreService.numeroValoriRicevuti();
    }

    /**
     * Restituisce tutti i valori presenti nel DB per un determinato Sensore Installato
     * @param idSensoreInstallato id del sensore
     * @return una lista di oggetti della classe una lista di oggetti di {@link com.progettoingegneria.entity.Valore}
     */
    @RequestMapping(value="/getValore/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Valore> getValore(@PathVariable("id") Long idSensoreInstallato) {

        return sensoreService.ricercaValorePerIdSensore(idSensoreInstallato);

    }

    /**
     * Inserisce un nuovo valore nel DB
     * @param stringaSensore Stringa del body proveniente da un sensore
     */
    @RequestMapping(value="/insertValore", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
    public void insertValore(@RequestBody String stringaSensore) { //inserisce il body della post in una stringa

            /*
            * la stringa sensore in ingresso
            * <identificatore><stringa cifre decimali><stringa caratteri>.
             */
        String split[] = stringaSensore.split("><");
        String identificatore = split[0].substring(1);  //id sensore installato
        String stringaCifre = split[1];
        String stringaCaratteri = split[2].substring(0,split[2].length() -1);
        sensoreService.insertValore(Long.valueOf(identificatore),stringaCifre, stringaCaratteri);
    }

    /**
     * Restituisce il numero di valori errati ricevuti dai sensori
     * @return
     */
    @RequestMapping(value = "/getErroriRilevati", method = RequestMethod.GET)
    public int getErroriRilevati(){
        return sensoreService.numeroErroriRilevati();
    }


    @RequestMapping(value = {"/export/{idSensoreInstallato}/{limit}","/export/{idSensoreInstallato}"}, method = RequestMethod.GET, produces = "text/csv")
    public ResponseEntity export(@PathVariable("idSensoreInstallato") Long idSensoreInstallato, @PathVariable("limit") Optional<Integer> limit){
        StringBuilder resource = new StringBuilder();

        int conta = 0;
        for(Valore val : sensoreService.ricercaValorePerIdSensore(idSensoreInstallato)){
            if(limit.isPresent()){
                if(conta >= limit.get().intValue()){
                    break;
                }
            }
            resource.append(val.toCsvLine());
            resource.append("\n");
            conta+=1;
        }

        return ResponseEntity.ok()
                .contentLength(resource.length())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource.toString());
    }



}
