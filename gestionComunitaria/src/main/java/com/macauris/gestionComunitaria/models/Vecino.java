package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import java.util.Set;
import java.util.List;
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
    private List<Vivienda> viviendas;
    
    @ManyToMany(mappedBy = "vecinos")
    @JsonIgnore
    private Set<Rol> roles;

    @ManyToMany(mappedBy = "vecinos")
    @JsonIgnore
    private Set<Mensualidad> mensualidades;

    @ManyToMany(mappedBy = "vecinos")
    @JsonIgnore
    private Set<Comision> comisiones;

    @ManyToMany(mappedBy = "vecinos")
    @JsonIgnore
    private Set<Reunion> reuniones;

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

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Set<Mensualidad> getMensualidades() {
        return mensualidades;
    }

    public void setMensualidades(Set<Mensualidad> mensualidades) {
        this.mensualidades = mensualidades;
    }

    public Set<Comision> getComisiones() {
        return comisiones;
    }

    public void setComisiones(Set<Comision> comisiones) {
        this.comisiones = comisiones;
    }

    public Set<Reunion> getReuniones() {
        return reuniones;
    }

    public void setReuniones(Set<Reunion> reuniones) {
        this.reuniones = reuniones;
    }
   
}