package com.macauris.gestionComunitaria.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @ManyToOne
    @JoinColumn(name = "vivienda_id",nullable = false)
    private Vivienda vivienda; 
    
    // Getters y Setters
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
    
    // Getters y Setters de la relacion
    public Vivienda getVivienda() {
        return vivienda;
    }
    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }
    //
}
/*

ClasesGeneradasPorGepeto
package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vecinos")
public class Vecino {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "vivienda_id", nullable = false)
    private Vivienda vivienda;

    // Getters y Setters
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
    public Vivienda getVivienda() {
        return vivienda;
    }
    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }
}

 */