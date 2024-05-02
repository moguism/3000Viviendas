/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="TiposLocales")
public class TipoLocales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    public Long idTiposLocales;
    
    public String NombreTipo;

    public Long getIdTiposLocales() {
        return idTiposLocales;
    }

    public String getNombreTipo() {
        return NombreTipo;
    }

    public void setIdTiposLocales(Long idTiposLocales) {
        this.idTiposLocales = idTiposLocales;
    }

    public void setNombreTipo(String NombreTipo) {
        this.NombreTipo = NombreTipo;
    }
    
    
}
