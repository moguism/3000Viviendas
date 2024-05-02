
package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name="Ingresos")
public class Ingreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idingresos;
    
    public String NombreIngreso;

    public Long getIdingresos() {
        return idingresos;
    }

    public void setIdingresos(Long idingresos) {
        this.idingresos = idingresos;
    }

    public String getNombreIngreso() {
        return NombreIngreso;
    }

    public void setNombreIngreso(String NombreIngreso) {
        this.NombreIngreso = NombreIngreso;
    }
    
    
}
