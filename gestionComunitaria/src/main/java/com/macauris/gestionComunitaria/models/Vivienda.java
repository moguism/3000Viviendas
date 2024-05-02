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
    
}
