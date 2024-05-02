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
import java.util.Date;

@Entity
@Table (name="Deudas")
public class Deuda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idDeudas;
    
    public Long TiposDeuda_idTipoDeuda;
    
    public Long Comunidades_idComunidades;
    
    public Date Created_at;

    public Long getIdDeudas() {
        return idDeudas;
    }

    public void setIdDeudas(Long idDeudas) {
        this.idDeudas = idDeudas;
    }

    public Long getTiposDeuda_idTipoDeuda() {
        return TiposDeuda_idTipoDeuda;
    }

    public void setTiposDeuda_idTipoDeuda(Long TiposDeuda_idTipoDeuda) {
        this.TiposDeuda_idTipoDeuda = TiposDeuda_idTipoDeuda;
    }

    public Long getComunidades_idComunidades() {
        return Comunidades_idComunidades;
    }

    public void setComunidades_idComunidades(Long Comunidades_idComunidades) {
        this.Comunidades_idComunidades = Comunidades_idComunidades;
    }

    public Date getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(Date Created_at) {
        this.Created_at = Created_at;
    }

    
}
