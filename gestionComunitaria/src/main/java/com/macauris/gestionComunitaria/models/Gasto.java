
package com.macauris.gestionComunitaria.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;


@Entity
@Table(name="gastos")
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    public String nombre;

    @ManyToMany(mappedBy = "gastos")
    @JsonIgnore
    private Set<Comunidad> comunidades;

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

    public Set<Comunidad> getComunidades() {
        return comunidades;
    }

    public void setComunidades(Set<Comunidad> comunidades) {
        this.comunidades = comunidades;
    }

    
    
}
