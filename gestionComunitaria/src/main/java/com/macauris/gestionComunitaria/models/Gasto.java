
package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
@Table(name="gastos")
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idgastos;
    
    public String NombreGasto;
    

    public Long getIdgastos() {
        return idgastos;
    }

    public void setIdgastos(Long idgastos) {
        this.idgastos = idgastos;
    }

    public String getNombreGasto() {
        return NombreGasto;
    }

    public void setNombreGasto(String NombreGasto) {
        this.NombreGasto = NombreGasto;
    }
    
    
}
