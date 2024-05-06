
package com.macauris.gestionComunitaria.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contratos")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    public Long contratante_id;
    
    public Long tipo_contrato_id;
    
    public Long comunidad_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContratante_id() {
        return contratante_id;
    }

    public void setContratante_id(Long contratante_id) {
        this.contratante_id = contratante_id;
    }

    public Long getTipo_contrato_id() {
        return tipo_contrato_id;
    }

    public void setTipo_contrato_id(Long tipo_contrato_id) {
        this.tipo_contrato_id = tipo_contrato_id;
    }

    public Long getComunidad_id() {
        return comunidad_id;
    }

    public void setComunidad_id(Long comunidad_id) {
        this.comunidad_id = comunidad_id;
    }
    
}
