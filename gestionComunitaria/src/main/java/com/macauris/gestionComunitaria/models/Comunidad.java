package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // LE DICE QUE ES UNA TABLA DE LA BASE DE DATOS (le sirve a Hibernate)
@Table(name="Comunidades") // LE DICE EL NOMBRE EXACTO DE LA TABLA
public class Comunidad {
    
    @Id // LE DICE CUÁL ES LA PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // lE DICE QUE LA PK ES AUTO-INCREMENTAL, POR LO QUE NO TIENE QUE ESPECIFICAR UN ID CUANDO SE INSERTEN DATOS
    private Long idComunidades;
    
    @Column(name = "nombreComunidad") // LE ESPECIFICA EL NOMBRE ESPECÍFICO DEL CAMPO
    private String nombreComunidad;
    
    private String direccionComunidad;
    
    // Getters
    
    public Long getIdComunidades() {
        
        return idComunidades;
        
    }

    public String getNombreComunidad() {
        
        return nombreComunidad;
        
    }
    
    public String getDireccionComunidad() {
        
        return direccionComunidad;
        
    }

    // Setters
    
    public void setNombreComunidad(String nombreComunidad) {
        
        this.nombreComunidad = nombreComunidad;
        
    }
    
    public void setIdComunidades(Long idComunidades) {
        
        this.idComunidades = idComunidades;
        
    }

    public void setDireccionComunidad(String direccionComunidad) {
        
        this.direccionComunidad = direccionComunidad;
        
    }
    
}
