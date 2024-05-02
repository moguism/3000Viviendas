
package com.macauris.gestionComunitaria.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contratante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idContratante;
    
    public String NombreContratante;

    public Long getIdContratante() {
        return idContratante;
    }

    public void setIdContratante(Long idContratante) {
        this.idContratante = idContratante;
    }

    public String getNombreContratante() {
        return NombreContratante;
    }

    public void setNombreContratante(String NombreContratante) {
        this.NombreContratante = NombreContratante;
    }
    
    
 
}