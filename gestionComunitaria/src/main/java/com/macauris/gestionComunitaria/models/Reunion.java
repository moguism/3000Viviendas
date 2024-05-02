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
@Table (name="Reuniones")

public class Reunion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    public Long idReuniones;
    
    public Long TipoReunion_idTipoReunion;
    
    public Date Fecha;

    public Long getIdReuniones() {
        return idReuniones;
    }

    public Long getTipoReunion_idTipoReunion() {
        return TipoReunion_idTipoReunion;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setIdReuniones(Long idReuniones) {
        this.idReuniones = idReuniones;
    }

    public void setTipoReunion_idTipoReunion(Long TipoReunion_idTipoReunion) {
        this.TipoReunion_idTipoReunion = TipoReunion_idTipoReunion;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }
    
    
}
