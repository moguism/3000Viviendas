package com.macauris.gestionComunitaria.models;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//Los @Column segun la documentacion de jakarta no hace falta, debido a que tiene el mismo nombre de la base de datos, pero bueno por algun tipo de "aclaracion". 

@Entity
@Table(name = "viviendas")
public class Vivienda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "bloque_id", nullable = false)
    private Bloque bloque;  //Hace referencia al objeto al que se relaciona no a la tabla en si de la bbdd
    
    //@Column(name = "escalera")
    public String escalera; 

    //@Column(name = "planta")
    public String planta; //Tendria que ser un Long

    //@Column(name = "puerta")
    public String puerta;
    
    //@Column(name = "letra")
    public String letra;

    //Referencia a la clase vecino y no a la relacion en la bbdd
    @OneToMany(mappedBy = "vivienda" , cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Vecino> vecinos;

   

   
    //
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   /*
    Tecnicamente no hace falta ya que se hace referencia al bloque a traves de la relacion

    public Long getBloque_id() {
        return bloque_id;
    }

    public void setBloque_id(Long bloque_id) {
        this.bloque_id = bloque_id;
    }
 */
    public String getEscalera() {
        return escalera;
    }

    public void setEscalera(String escalera) {
        this.escalera = escalera;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public String getPuerta() {
        return puerta;
    }

    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
    //Intento Ismael: FK

    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }

    public Set<Vecino> getVecinos() {
        return vecinos;
    }

    public void setVecinos(Set<Vecino> vecinos) {
        this.vecinos = vecinos;
    }
    
    
    
}

/*GeneradoPorGepeto
package com.macauris.gestionComunitaria.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "viviendas")
public class Vivienda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "bloque_id", nullable = false)
    private Bloque bloque;
    @OneToMany(mappedBy = "vivienda", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Vecino> vecinos;

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public Set<Vecino> getVecinos() {
        return vecinos;
    }
    public void setVecinos(Set<Vecino> vecinos) {
        this.vecinos = vecinos;
    }
}
 */