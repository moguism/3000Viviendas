package com.macauris.gestionComunitaria.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name = "mensualidades")
public class Mensualidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double cuantia;

    private Date fecha;

    @OneToMany(mappedBy = "mensualidad", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "mensualidad-vivienda")
    private List<Vivienda> viviendas;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bloque_id")
    @JsonBackReference(value="bloque-mensualidad")
    private Bloque bloque;

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

    public List<Vivienda> getViviendas() {
        return viviendas;
    }

    public void setViviendas(List<Vivienda> viviendas) {
        this.viviendas = viviendas;
    }

    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

}
