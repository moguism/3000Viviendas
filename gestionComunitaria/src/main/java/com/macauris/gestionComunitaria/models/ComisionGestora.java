package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name="ComisionGestora")
public class ComisionGestora {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComisionGestora;
    private Date Created_at;
    private Date Updated_at;

    public Long getIdComisionGestora() {
        return idComisionGestora;
    }

    public void setIdComisionGestora(Long idComisionGestora) {
        this.idComisionGestora = idComisionGestora;
    }

    public Date getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(Date Created_at) {
        this.Created_at = Created_at;
    }

    public Date getUpdated_at() {
        return Updated_at;
    }

    public void setUpdated_at(Date Updated_at) {
        this.Updated_at = Updated_at;
    }
    
}
