/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table (name="reuniones")

public class Reunion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Date fecha;

    @ManyToMany
    @JoinTable(
        name = "reuniones_vecinos",
        joinColumns = @JoinColumn(name = "reunion_id"),
        inverseJoinColumns = @JoinColumn(name = "vecino_id")
    )
    @JsonIgnore
    private Set<Vecino> vecinos;
    
    @ManyToMany(mappedBy = "reuniones")
    @JsonIgnore
    private Set<Evento> eventos;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_reunion_id")
    @JsonManagedReference
    private TipoReunion tipoReunion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<Vecino> getVecinos() {
        return vecinos;
    }

    public void setVecinos(Set<Vecino> vecinos) {
        this.vecinos = vecinos;
    }

    public TipoReunion getTipoReunion() {
        return tipoReunion;
    }

    public void setTipoReunion(TipoReunion tipoReunion) {
        this.tipoReunion = tipoReunion;
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }

}
