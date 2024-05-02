package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name="Mensualidades")
public class Mensualidad {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMensualidades;
    private Date Fecha;

    public Long getIdMensualidades() {
        return idMensualidades;
    }

    public void setIdMensualidades(Long idMensualidades) {
        this.idMensualidades = idMensualidades;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }
    
}
