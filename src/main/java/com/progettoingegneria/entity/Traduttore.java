package com.progettoingegneria.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Classe Entity per contenere un Traduttore, chiamato anche adattatore,
 * usata per parsare il messaggio inviato dal sensore
 */
@Entity
@Table(name = "TRADUTTORE")
public class Traduttore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "traduttore_id")
    private Long traduttoreId;

    @NotNull
    private String carattereSplit;

    @NotNull
    private Integer posizioneData;

    @NotNull
    private Integer posizioneCodice;

    @NotNull
    private Integer posizioneValore;

    @NotNull
    private String formatoData;

    public Traduttore(){};

    public Traduttore(String carattereSplit, Integer posizioneData, Integer posizioneCodice, Integer posizioneValore, String formatoData) {
        this.carattereSplit = carattereSplit;
        this.posizioneData = posizioneData;
        this.posizioneCodice = posizioneCodice;
        this.posizioneValore = posizioneValore;
        this.formatoData = formatoData;
    }

    //GET_SET


    public Long getTraduttoreId() {
        return traduttoreId;
    }

    public void setTraduttoreId(Long traduttoreId) {
        this.traduttoreId = traduttoreId;
    }

    public String getCarattereSplit() {
        return carattereSplit;
    }

    public void setCarattereSplit(String carattereSplit) {
        this.carattereSplit = carattereSplit;
    }

    public Integer getPosizioneData() {
        return posizioneData;
    }

    public void setPosizioneData(Integer posizioneData) {
        this.posizioneData = posizioneData;
    }

    public Integer getPosizioneCodice() {
        return posizioneCodice;
    }

    public void setPosizioneCodice(Integer posizioneCodice) {
        this.posizioneCodice = posizioneCodice;
    }

    public Integer getPosizioneValore() {
        return posizioneValore;
    }

    public void setPosizioneValore(Integer posizioneValore) {
        this.posizioneValore = posizioneValore;
    }

    public String getFormatoData() {
        return formatoData;
    }

    public void setFormatoData(String formatoData) {
        this.formatoData = formatoData;
    }
}
