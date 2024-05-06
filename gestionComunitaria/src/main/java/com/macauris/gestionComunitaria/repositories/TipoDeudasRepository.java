package com.macauris.gestionComunitaria.repositories;

import com.macauris.gestionComunitaria.models.TipoDeudas;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoDeudasRepository extends JpaRepository<TipoDeudas, Long> {
    
}
