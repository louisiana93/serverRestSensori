package com.progettoingegneria.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Classe Entity per contenere un sensore
 */
@Entity
@Table(name = "SENSORE")
public class Sensore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sensoreId;

    @NotNull
    private String tipo;

    @NotNull
    private String marca;

    @NotNull
    private long adattatoreId;

    @NotNull
    private String intervallo;



    public Sensore(){};

    public Sensore(String tipo, String marca, long adattatoreId, String intervallo) {
        this.tipo = tipo;
        this.marca = marca;
        this.adattatoreId = adattatoreId;
        this.intervallo = intervallo;
    }

//GET-SET

    public Long getSensoreId() {
        return sensoreId;
    }

    public void setSensoreId(Long sensoreId) {
        this.sensoreId = sensoreId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Long getAdattatoreId() {
        return adattatoreId;
    }

    public void setAdattatoreId(Long adattatoreId) {
        this.adattatoreId = adattatoreId;
    }

    public String getIntervallo() {
        return intervallo;
    }

    public void setIntervallo(String intervallo) {
        this.intervallo = intervallo;
    }
}
