package com.progettoingegneria.controller;

import com.progettoingegneria.entity.Utente;
import com.progettoingegneria.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest Controller per gestire il Login Utent e la gestione Utente
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UtenteService utenteService;

    /**
     * Per inserire un utente nel db, si aspetta in input un json
     * @param utente Utente da inserire
     */
    @RequestMapping(value = "/insertUtente", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertUser(@RequestBody Utente utente) { utenteService.insertUtente(utente); }

    /**
     * Per eliminare un utente a partire dal suo id
     * @param idUtente
     */
    @RequestMapping(value = "/deleteUtente/{id}", method = RequestMethod.GET)
    public void deleteUtente(@PathVariable("id") Long idUtente){ utenteService.deleteUtenteDB(idUtente); }

    /**
     * Restituisce la lista degli utenti presenti in DB
     * @return
     */
    @RequestMapping(value = "/getUtenti", method = RequestMethod.GET)
    public List<Utente> getListaUtenti() {return utenteService.getUtenti();}

    /**
     * Usato per il Login Utente
     * @param utente
     * @return Ritorna un Utente con valorizzati il campo UserName e il campo Ruolo
     */
    @RequestMapping(value = "/checkUtente", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Utente checkUser(@RequestBody Utente utente){
        return utenteService.controllaUtente(utente.getUsername(), utente.getPassword().toUpperCase());
    }
}
