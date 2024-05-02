package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Viviendas")
public class Vivienda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idViviendas;
    
    public Long Bloques_idBloques;
    
    public String Escalera;
    
    public String Planta;
    
    public String Puerta;
    
    public String Letra;
    
    // Getters

    public Long getIdViviendas() {
        
        return idViviendas;
        
    }

    public Long getBloques_idBloques() {
        
        return Bloques_idBloques;
        
    }

    public String getEscalera() {
        
        return Escalera;
        
    }

    public String getPlanta() {
        
        return Planta;
        
    }

    public String getPuerta() {
        
        return Puerta;
        
    }

    public String getLetra() {
        
        return Letra;
        
    }
    
    // Setters

    public void setIdViviendas(Long idViviendas) {
        
        this.idViviendas = idViviendas;
        
    }

    public void setBloques_idBloques(Long Bloques_idBloques) {
        
        this.Bloques_idBloques = Bloques_idBloques;
        
    }

    public void setEscalera(String Escalera) {
        
        this.Escalera = Escalera;
        
    }

    public void setPlanta(String Planta) {
        
        this.Planta = Planta;
        
    }

    public void setPuerta(String Puerta) {
        
        this.Puerta = Puerta;
        
    }

    public void setLetra(String Letra) {
        
        this.Letra = Letra;
   
    }
    
}
