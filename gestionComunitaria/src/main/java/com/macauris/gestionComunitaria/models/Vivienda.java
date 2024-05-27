package com.macauris.gestionComunitaria.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Set;

//Los @Column segun la documentacion de jakarta no hace falta, debido a que tiene el mismo nombre de la base de datos, pero bueno por algun tipo de "aclaracion". 

@Entity
@Table(name = "viviendas")
public class Vivienda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "bloque_id")
    @JsonBackReference(value = "bloque-vivienda")
    private Bloque bloque;  //Hace referencia al objeto al que se relaciona no a la tabla en si de la bbdd
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vecino_id")
    @JsonBackReference(value = "vecino-vivienda")
    private Vecino vecino;
    
    private String escalera;
    private String puerta;
    private String planta;
    private String letra;
    
    @ManyToMany(mappedBy = "viviendas")
    @JsonBackReference(value = "mensualidad-vivienda")
    @JsonIgnore
    private Set<Mensualidad> mensualidades;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public Vecino getVecino() {
        return vecino;
    }

    public void setVecino(Vecino vecino) {
        this.vecino = vecino;
    }

    public String getEscalera() {
        return escalera;
    }

    public void setEscalera(String escalera) {
        this.escalera = escalera;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Set<Mensualidad> getMensualidades() {
        return mensualidades;
    }

    public void setMensualidades(Set<Mensualidad> mensualidades) {
        this.mensualidades = mensualidades;
    }
    
}