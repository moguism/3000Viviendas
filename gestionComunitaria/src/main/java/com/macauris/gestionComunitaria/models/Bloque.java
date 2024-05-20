package com.macauris.gestionComunitaria.models;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
//Falta un nombre, y una posible direccion o numero de bloque por si existen varios bloques en la misma calle/comunidad/urbanizacion
@Entity
@Table(name="bloques")
public class Bloque {
    
    // Atributos
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //"Indica" que es autoincremental
    @Column(name = "bloque_id")
    private Long id;
    //private Long comunidad_id;ç
    @OneToMany(mappedBy = "bloque") //Relacion uno a muchos
    public Set<Vivienda> listaViviendas; 
    @ManyToOne //Relacion muchos a uno
    @JoinColumn(name = "comunidad_id",nullable = false)
    private Comunidad comunidad;

    

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


    public Comunidad getComunidad() {
        return comunidad;
    }
    

    public void setComunidad(Comunidad comunidad) {
        this.comunidad = comunidad;
    }

    public Set<Vivienda> getViviendas() {
        return listaViviendas;
    }

    public void setViviendas(Set<Vivienda> listaViviendas) {
        this.listaViviendas = listaViviendas;
    }
    
    
}
