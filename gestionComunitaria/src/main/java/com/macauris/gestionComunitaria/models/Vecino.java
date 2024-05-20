/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vecinos")
public class Vecino {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Long vivienda_id;
    //Intento Ismael: FK
    @ManyToOne
    @JoinColumn(name = "vivienda_id",nullable = false)
    private Vivienda vivienda; 
    //
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Long getVivienda_id() {
        return vivienda_id;
    }
    public void setVivienda_id(Long vivienda_id) {
        this.vivienda_id = vivienda_id;
    }
    //Intento Ismael: FK
    public Vivienda getVivienda() {
        return vivienda;
    }
    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }
    //
}
