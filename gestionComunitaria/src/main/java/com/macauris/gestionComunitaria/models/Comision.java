package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="comisiones")
public class Comision {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date created_at;
    private Date updated_at;

    @ManyToMany
    @JoinTable(
        name = "comisiones_vecinos",
        joinColumns = @JoinColumn(name = "comision_id"),
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
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    public Set<Vecino> getVecinos() {
        return vecinos;
    }
    public void setVecinos(Set<Vecino> vecinos) {
        this.vecinos = vecinos;
    }
    
}
