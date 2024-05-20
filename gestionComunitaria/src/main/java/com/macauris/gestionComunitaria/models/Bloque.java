package com.macauris.gestionComunitaria.models;
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
@Table(name="bloques")
public class Bloque {
    
    // Atributos
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //"Indica" que es autoincremental
    private Long id;
    //private Long comunidad_id;
    @ManyToOne //Relacion muchos a uno
    @JoinColumn(name = "comunidad_id",nullable = false)
    private Comunidad comunidad;
    @OneToMany(mappedBy = "bloque")
    public Set<Vivienda> viviendas; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*Tecnicamente no hace falta, ya que se hace referencia a la comunidad a traves de la relacion

    public Long getComunidad_id() {
        return comunidad_id;
    }

    public void setComunidad(Long comunidad_id) {
        this.comunidad_id = comunidad_id;
    }
 */

 //Intento Ismael: FK
    public Comunidad getComunidad() {
        return comunidad;
    }

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    public Set<Vivienda> getViviendas() {
        return viviendas;
    }

    public void setViviendas(Set<Vivienda> viviendas) {
        this.viviendas = viviendas;
    }
    
    
}
