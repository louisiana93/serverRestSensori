package com.progettoingegneria.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Classe Entity per contenere un Utente
 */
@Entity
@Table(name = "UTENTE")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long utenteId;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String ruolo;

    public Utente(){};

    public Utente(String username, String password, String ruolo) {
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
    }

    //GET SET
    public Long getUtenteId() {
        return utenteId;
    }

    public void setUtenteId(Long utenteId) {
        this.utenteId = utenteId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
