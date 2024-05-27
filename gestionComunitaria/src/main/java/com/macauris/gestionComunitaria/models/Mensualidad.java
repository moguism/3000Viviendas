package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "mensualidades")
public class Mensualidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double cuantia;

    private Date fecha;

    @ManyToMany
    @JoinTable(
        name = "mensualidades_viviendas",
        joinColumns = @JoinColumn(name = "mensualidad_id"),
        inverseJoinColumns = @JoinColumn(name = "vivienda_id")
    )
    @JsonManagedReference(value = "mensualidad-vivienda")
    private Set<Vivienda> viviendas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCuantia() {
        return cuantia;
    }

    public void setCuantia(double cuantia) {
        this.cuantia = cuantia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<Vivienda> getViviendas() {
        return viviendas;
    }

    public void setViviendas(Set<Vivienda> viviendas) {
        this.viviendas = viviendas;
    }

}
