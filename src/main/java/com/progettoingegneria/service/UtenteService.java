package com.progettoingegneria.service;

import com.progettoingegneria.dao.UtenteDao;
import com.progettoingegneria.entity.Utente;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe Service per gestire la logica per gli Utenti
 */
@Service
public class UtenteService {

    @Autowired
    private UtenteDao utenteDao;


    private static final Logger logger = LoggerFactory.getLogger(UtenteService.class);

    /**
     * Inserisce un Utente nel DB.
     * @param u oggetto della classe {@link com.progettoingegneria.entity.Utente}
     */
    public void insertUtente(Utente u){
       String pass = u.getPassword().toUpperCase();
       u.setPassword(pass);
       utenteDao.save(u);
    }

    /**
     * Restisce tutti gli utenti presenti nel DB
     * @return lista di oggetti della classe {@link com.progettoingegneria.entity.Utente}
     */
    public List<Utente> getUtenti(){
        return utenteDao.findAll();
    }

    /**
     * Controlla username e password per il Login.
     * In caso di utente non trovato o password sbagliata restituisce un oggetto della classe
     * {@link com.progettoingegneria.entity.Utente} con username settate a "0".
     * In caso di successo restituisce un oggetto della classe {@link com.progettoingegneria.entity.Utente}
     * con valorizzati username e ruolo.
     * @param username
     * @param password
     * @return oggetto della classe {@link com.progettoingegneria.entity.Utente}
     */
    public Utente controllaUtente(String username, String password){
    //la password arriva già cifrata
        Utente res = new Utente();
        Utente utenteTrovato = utenteDao.findByUsername(username);

        if(utenteTrovato == null) {
            res.setUsername("0"); //utente non trovato
            return res;
        }

        //utente trovato e controllo la password
        if(utenteTrovato.getPassword().equals(password)){
            //return utenteTrovato.getRuolo();
            res.setUsername(utenteTrovato.getUsername());
            res.setRuolo(utenteTrovato.getRuolo());
        }
        else{
            res.setUsername("0");
        }
        return res;

    }

    /**
     * Elimina un utente dal DB a partire dal suo id
     * @param idUtente Long id dell'utente
     */
    public void deleteUtenteDB(Long idUtente){
        utenteDao.delete(idUtente);
    }
}
