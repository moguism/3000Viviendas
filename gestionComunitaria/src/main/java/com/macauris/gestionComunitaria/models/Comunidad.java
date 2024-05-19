package com.macauris.gestionComunitaria.models;
import java.util.Set; //
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name="comunidades")
public class Comunidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nombre;
    //@Column(name = "direccion_comunidad")
    public String direccion;

    //Intento Ismael: FK
    @OneToMany(mappedBy = "comunidad")
    public Set<Bloque> listaBloques; // Si lo haces segun documentacion de jakarta, da error.
    //

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    //Intento Ismael: FK

    public Set<Bloque> getBloques() {
        return listaBloques;
    }

    public void setBloques(Set<Bloque> listaBloques) {
        this.listaBloques = listaBloques;
    }
    //
    
}


