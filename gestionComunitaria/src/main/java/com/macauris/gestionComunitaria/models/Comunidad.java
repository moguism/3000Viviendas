package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
@Table(name="Comunidades")
public class Comunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idComunidades;
    
    //@Column(name = "nombre_comunidad")
    public String nombreComunidad;
    
    //@Column(name = "direccion_comunidad")
    public String direccionComunidad;

    
    public Long getIdComunidades() {
        return idComunidades;
    }

    public void setIdComunidades(Long idComunidades) {
        this.idComunidades = idComunidades;
    }

    public String getNombreComunidad() {
        return nombreComunidad;
    }

    public void setNombreComunidad(String nombreComunidad) {
        this.nombreComunidad = nombreComunidad;
    }

    public String getDireccionComunidad() {
        return direccionComunidad;
    }

    public void setDireccionComunidad(String direccionComunidad) {
        this.direccionComunidad = direccionComunidad;
    }
    
    
}


