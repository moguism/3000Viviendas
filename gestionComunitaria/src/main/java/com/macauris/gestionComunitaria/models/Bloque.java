package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Bloques")
public class Bloque {
    
    // Atributos
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloques;
    
    private Long Comunidades_idComunidades;
    
    // Getters

    public Long getIdBloques() {
        
        return idBloques;
        
    }

    public Long getComunidades_idComunidades() {
        
        return Comunidades_idComunidades;
        
    }
    
    // Setters

    public void setIdBloques(Long idBloques) {
        
        this.idBloques = idBloques;
        
    }

    public void setComunidades_idComunidades(Long Comunidades_idComunidades) {
        
        this.Comunidades_idComunidades = Comunidades_idComunidades;
        
    }
    
}
