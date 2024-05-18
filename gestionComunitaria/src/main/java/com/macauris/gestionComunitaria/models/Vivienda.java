package com.macauris.gestionComunitaria.models;
import java.util.Set;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "viviendas")
public class Vivienda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    public Long bloque_id;
    
    public String escalera;

    public String planta;
    
    public String puerta;
    
    public String letra;
    //Intento Ismael: FK

    @ManyToOne
    @JoinColumn(name = "bloque_id",nullable = false)
    private Bloque bloque;

    @OneToMany(mappedBy = "vivienda")
    public Set<Vecino> vecinos;
    //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBloque_id() {
        return bloque_id;
    }

    public void setBloque_id(Long bloque_id) {
        this.bloque_id = bloque_id;
    }

    public String getEscalera() {
        return escalera;
    }

    public void setEscalera(String escalera) {
        this.escalera = escalera;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
    //Intento Ismael: FK

    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public Set<Vecino> getVecinos() {
        return vecinos;
    }

    public void setVecinos(Set<Vecino> vecinos) {
        this.vecinos = vecinos;
    }
    
    
    
}
