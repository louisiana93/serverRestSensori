package com.progettoingegneria.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Classe Entity per contenere i valori inviati dai sensori
 */
@Entity
@Table(name = "VALORE")
public class Valore {
    @Id //specifica che si tratta della chiave primaria
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long valoreId;

    @NotNull
    private Long idSensoreInstallato; //id sensore installato

    //puo essere nullo
    private String valoreAcquisito; //stringa di valori numerici da decodificare

    @NotNull
    private String codiceAcquisito;

    @NotNull
    private String messaggio; //stringaDiCaratteri

    @NotNull
    private String dati; //stringa di valori numerici da decodificare

    private Timestamp data_acquisizione; //dataOra della acquisizione estratta dai dati

    public Valore(){};

    public Valore(Long idSensoreInstallato, String valoreAcquisito, String codiceAcquisito, String dati, String messaggio, Timestamp data_acquisizione) {
        this.idSensoreInstallato = idSensoreInstallato;
        this.valoreAcquisito = valoreAcquisito;
        this.codiceAcquisito = codiceAcquisito;
        this.dati = dati;
        this.messaggio = messaggio;
        this.data_acquisizione = data_acquisizione;
    }

    public String toCsvLine(){
        return idSensoreInstallato+","+valoreAcquisito+","+data_acquisizione.toString();
    }


//GET_SET


    public Long getValoreId() {
        return valoreId;
    }

    public void setValoreId(Long valoreId) {
        this.valoreId = valoreId;
    }

    public Long getIdSensoreInstallato() {
        return idSensoreInstallato;
    }

    public void setIdSensoreInstallato(Long idSensoreInstallato) {
        this.idSensoreInstallato = idSensoreInstallato;
    }

    public String getValoreAcquisito() {
        return valoreAcquisito;
    }

    public void setValoreAcquisito(String valoreAcquisito) {
        this.valoreAcquisito = valoreAcquisito;
    }

    public String getCodiceAcquisito() {
        return codiceAcquisito;
    }

    public void setCodiceAcquisito(String codiceAcquisito) {
        this.codiceAcquisito = codiceAcquisito;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getDati() {
        return dati;
    }

    public void setDati(String dati) {
        this.dati = dati;
    }

    public Timestamp getData_acquisizione() {
        return data_acquisizione;
    }

    public void setData_acquisizione(Timestamp data_acquisizione) {
        this.data_acquisizione = data_acquisizione;
    }
}
