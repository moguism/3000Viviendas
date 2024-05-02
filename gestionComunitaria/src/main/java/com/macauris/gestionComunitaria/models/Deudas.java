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
import java.util.Date;

@Entity
@Table (name="Deudas")
public class Deudas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idDeudas;
    
    public Long TiposDeuda_idTipoDeuda;
    
    public Long Comunidades_idComunidades;
    
    public Date Created_at;

    public Long getIdDeudas() {
        return idDeudas;
    }

    public Long getIdTiposDeudas() {
        return idTiposDeudas;
    }

    public Long getIdComunidades() {
        return idComunidades;
    }

    public Date getCreate_at() {
        return Create_at;
    }

    public void setIdDeudas(Long idDeudas) {
        this.idDeudas = idDeudas;
    }

    public void setIdTiposDeudas(Long idTiposDeudas) {
        this.idTiposDeudas = idTiposDeudas;
    }

    public void setIdComunidades(Long idComunidades) {
        this.idComunidades = idComunidades;
    }

    public void setCreate_at(Date Create_at) {
        this.Create_at = Create_at;
    }
    
    
}
