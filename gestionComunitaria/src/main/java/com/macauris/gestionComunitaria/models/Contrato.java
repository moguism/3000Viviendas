
package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idContrato;
    
    public Long Contratante_idContratante;
    
    public Long TiposContrato_idTiposContrato;
    
    public Long Comunidades_idComunidades;

    public Long getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Long idContrato) {
        this.idContrato = idContrato;
    }

    public Long getContratante_idContratante() {
        return Contratante_idContratante;
    }

    public void setContratante_idContratante(Long Contratante_idContratante) {
        this.Contratante_idContratante = Contratante_idContratante;
    }

    public Long getTiposContrato_idTiposContrato() {
        return TiposContrato_idTiposContrato;
    }

    public void setTiposContrato_idTiposContrato(Long TiposContrato_idTiposContrato) {
        this.TiposContrato_idTiposContrato = TiposContrato_idTiposContrato;
    }

    public Long getComunidades_idComunidades() {
        return Comunidades_idComunidades;
    }

    public void setComunidades_idComunidades(Long Comunidades_idComunidades) {
        this.Comunidades_idComunidades = Comunidades_idComunidades;
    }
    
    
}
