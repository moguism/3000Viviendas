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
@Table (name="Locales")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    public Long idLocales;
    
    public Long Comunidades_idComunidades;
    
    public Long TiposLocales_idTiposLocales;
    
    public String Nombrelocal;

    public Long getIdLocales() {
        return idLocales;
    }

    public Long getComunidades_idComunidades() {
        return Comunidades_idComunidades;
    }

    public Long getTiposLocales_idTiposLocales() {
        return TiposLocales_idTiposLocales;
    }

    public String getNombrelocal() {
        return Nombrelocal;
    }

    public void setIdLocales(Long idLocales) {
        this.idLocales = idLocales;
    }

    public void setComunidades_idComunidades(Long Comunidades_idComunidades) {
        this.Comunidades_idComunidades = Comunidades_idComunidades;
    }

    public void setTiposLocales_idTiposLocales(Long TiposLocales_idTiposLocales) {
        this.TiposLocales_idTiposLocales = TiposLocales_idTiposLocales;
    }

    public void setNombrelocal(String Nombrelocal) {
        this.Nombrelocal = Nombrelocal;
    }
    
    
}
