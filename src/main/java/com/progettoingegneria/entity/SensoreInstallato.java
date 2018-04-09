package com.progettoingegneria.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Classe Entity riferita al sensore installato in una posizione di un sito
 */
@Entity
@Table(name = "SENSORE_INSTALLATO")
public class SensoreInstallato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long sensoreInstallatoId;

    @NotNull
    Long posizioneId;

    @NotNull
    Long sensoreId;




    Timestamp dataInstallazione;

    public SensoreInstallato(){}

    public SensoreInstallato(Long posizioneId, Long sensoreId, Timestamp dataInstallazione) {
        this.posizioneId = posizioneId;
        this.sensoreId = sensoreId;
        this.dataInstallazione = dataInstallazione;
    }

    //GET E SET

    public Long getSensoreInstallatoId() {
        return sensoreInstallatoId;
    }

    public void setSensoreInstallatoId(Long sensoreInstallatoId) {
        this.sensoreInstallatoId = sensoreInstallatoId;
    }

    public Long getPosizioneId() {
        return posizioneId;
    }

    public void setPosizioneId(Long posizioneId) {
        this.posizioneId = posizioneId;
    }

    public Long getSensoreId() {
        return sensoreId;
    }

    public void setSensoreId(Long sensoreId) {
        this.sensoreId = sensoreId;
    }

    public Timestamp getDataInstallazione() {
        return dataInstallazione;
    }

    public void setDataInstallazione(Timestamp dataInstallazione) {
        this.dataInstallazione = dataInstallazione; }



}
