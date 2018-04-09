package com.progettoingegneria.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Classe Entity per la Posizione
 * Created by Luisian D'Anzi on 10/14/17.
 */
@Entity
@Table(name = "POSIZIONE")
public class Posizione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long posizioneId;

    // sito di riferimento
    @NotNull
    private Long sitoId;

    private String descrizione;

    //GET E SET

    public Long getPosizioneId() {
        return posizioneId;
    }

    public void setPosizioneId(Long posizioneId) {
        this.posizioneId = posizioneId;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Long getSitoId() {
        return sitoId;
    }

    public void setSitoId(Long sitoId) {
        this.sitoId = sitoId;
    }
}
