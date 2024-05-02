package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TiposContrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idTiposContrato;
    
    public String NombreTipoContrato;

    public Long getIdTiposContrato() {
        return idTiposContrato;
    }

    public void setIdTiposContrato(Long idTiposContrato) {
        this.idTiposContrato = idTiposContrato;
    }

    public String getNombreTipoContrato() {
        return NombreTipoContrato;
    }

    public void setNombreTipoContrato(String NombreTipoContrato) {
        this.NombreTipoContrato = NombreTipoContrato;
    }
    
    
}
