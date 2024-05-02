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
@Table (name="Eventos")


public class Eventos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    public Long idEventos;
    
    public String NombreEventos;

    public Long getIdEventos() {
        return idEventos;
    }

    public String getNombreEventos() {
        return NombreEventos;
    }

    public void setIdEventos(Long idEventos) {
        this.idEventos = idEventos;
    }

    public void setNombreEventos(String NombreEventos) {
        this.NombreEventos = NombreEventos;
    }
    
    
}
