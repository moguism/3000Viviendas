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
@Table (name="locales")
public class Local {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    public Long id;
    
    public Long comunidad_id;
    
    public Long tipo_local_id;
    
    public String nombre;

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

    public Long getTipo_local_id() {
        return tipo_local_id;
    }

    public void setTipo_local_id(Long tipo_local_id) {
        this.tipo_local_id = tipo_local_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
