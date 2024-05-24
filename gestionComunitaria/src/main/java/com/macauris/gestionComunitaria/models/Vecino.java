package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import java.util.Set;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//Prevision de futuro: No encuentro el jodido problema con Spring en esta puta clase y en la de vivienda, llegará mauricio el lunes a 3a y en aproximadamente 5 minutos lo arreglará.
//Los @Column segun la documentacion de jakarta no hace falta, debido a que tiene el mismo nombre de la base de datos, pero bueno por algun tipo de "aclaracion". 

@Entity
@Table(name = "vecinos")
public class Vecino {
    
    @Id // Indica que es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //@Column(name = "nombre") 
    private String nombre; 

    // private Long vivienda_id;  Esto supuestameno no es necesario, ni la documentacion ni gepeto dice nada

    @OneToMany(mappedBy = "vecino", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<Vivienda> viviendas;

    @ManyToMany(mappedBy = "vecinos")
    @JsonIgnore
    private Set<Mensualidad> mensualidades;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id")
    @JsonBackReference
    private Rol rol;

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

    public List<Vivienda> getViviendas() {
        return viviendas;
    }

    public void setViviendas(List<Vivienda> viviendas) {
        this.viviendas = viviendas;
    }

    public Set<Mensualidad> getMensualidades() {
        return mensualidades;
    }

    public void setMensualidades(Set<Mensualidad> mensualidades) {
        this.mensualidades = mensualidades;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
   
}