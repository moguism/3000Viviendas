package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="bloques")
public class Bloque {
    
    // Atributos
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long comunidad_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComunidad_id() {
        return comunidad_id;
    }

    public void setComunidad_id(Long comunidad_id) {
        this.comunidad_id = comunidad_id;
    }
    
    
    
}
