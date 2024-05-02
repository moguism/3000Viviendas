/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="TipoReunion")

public class TipoReunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     
    public Long idTipoReunion;
    
    public String NombreTipo;

    public Long getIdTipoReunion() {
        return idTipoReunion;
    }

    public String getNombreTipo() {
        return NombreTipo;
    }

    public void setIdTipoReunion(Long idTipoReunion) {
        this.idTipoReunion = idTipoReunion;
    }

    public void setNombreTipo(String NombreTipo) {
        this.NombreTipo = NombreTipo;
    }
    
    
}
