package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name="comisiones")
public class Comision {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComisionGestora;
    private Date created_at;
    private Date updated_at;
    
    public Long getIdComisionGestora() {
        return idComisionGestora;
    }
    public void setIdComisionGestora(Long idComisionGestora) {
        this.idComisionGestora = idComisionGestora;
    }
    public Date getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    public Date getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    
    
}
