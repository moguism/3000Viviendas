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
@Table (name="TipoDeudas")
public class TipoDeudas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    public Long idTipoDeudas;
    
    public String nombreDeuda;

    public Long getIdTipoDeudas() {
        return idTipoDeudas;
    }

    public String getNombreDeuda() {
        return nombreDeuda;
    }

    public void setIdTipoDeudas(Long idTipoDeudas) {
        this.idTipoDeudas = idTipoDeudas;
    }

    public void setNombreDeuda(String nombreDeuda) {
        this.nombreDeuda = nombreDeuda;
    }
    
    
}
