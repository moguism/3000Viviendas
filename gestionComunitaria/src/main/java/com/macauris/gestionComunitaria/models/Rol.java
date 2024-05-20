package com.macauris.gestionComunitaria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name="roles")
public class Rol {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany
    @JoinTable(
        name = "comisiones_vecinos",
        joinColumns = @JoinColumn(name = "rol_id"),
        inverseJoinColumns = @JoinColumn(name = "comision_id")
    )
    @JsonIgnore
    private Set<Comision> comisiones;

    @ManyToMany
    @JoinTable(
        name = "comisiones_vecinos",
        joinColumns = @JoinColumn(name = "rol_id"),
        inverseJoinColumns = @JoinColumn(name = "vecino_id")
    )
    @JsonIgnore
    private Set<Vecino> vecinos;

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

    public Set<Comision> getComisiones() {
        return comisiones;
    }

    public void setComisiones(Set<Comision> comisiones) {
        this.comisiones = comisiones;
    }

    public Set<Vecino> getVecinos() {
        return vecinos;
    }

    public void setVecinos(Set<Vecino> vecinos) {
        this.vecinos = vecinos;
    }

}
