package com.macauris.gestionComunitaria.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
//Falta un nombre, y una posible direccion o numero de bloque por si existen varios bloques en la misma calle/comunidad/urbanizacion

import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name="bloques")
public class Bloque {
    
    // Atributos
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //"Indica" que es autoincremental
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comunidad_id")
    @JsonManagedReference
    //@JsonIgnore
    private Comunidad comunidad;

    @OneToMany(mappedBy = "bloque", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<Vivienda> viviendas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    public List<Vivienda> getViviendas() {
        return viviendas;
    }

    public void setViviendas(List<Vivienda> viviendas) {
        this.viviendas = viviendas;
    }

}
