package com.progettoingegneria.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Classe Entity per contenere un sito
 */
@Entity
@Table(name = "SITO")
public class Sito {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sitoId;

    @NotNull
    private String tipo;

    @NotNull
    private String indirizzo;

    @NotNull
    private String descrizione;

    public Long getSitoId() {
        return sitoId;
    }

    public void setSitoId(Long sitoId) {
        this.sitoId = sitoId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
