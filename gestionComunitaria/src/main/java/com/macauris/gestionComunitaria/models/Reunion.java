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
@Table (name="reuniones")

public class Reunion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    public Long id;
    
    public Long tipo_reunion_id;
    
    public Date fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTipo_reunion_id() {
        return tipo_reunion_id;
    }

    public void setTipo_reunion_id(Long tipo_reunion_id) {
        this.tipo_reunion_id = tipo_reunion_id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
