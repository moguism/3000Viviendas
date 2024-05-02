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
@Table(name = "Vecinos")
public class Vecino {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idvecinos;
    private String nombreVecino;
    private Long Viviendas_idViviendas;

    public Long getIdvecinos() {
        return idvecinos;
    }

    public void setIdvecinos(Long idvecinos) {
        this.idvecinos = idvecinos;
    }

    public String getNombreVecino() {
        return nombreVecino;
    }

    public void setNombreVecino(String nombreVecino) {
        this.nombreVecino = nombreVecino;
    }

    public Long getViviendas_idViviendas() {
        return Viviendas_idViviendas;
    }

    public void setViviendas_idViviendas(Long Viviendas_idViviendas) {
        this.Viviendas_idViviendas = Viviendas_idViviendas;
    }
    
}
