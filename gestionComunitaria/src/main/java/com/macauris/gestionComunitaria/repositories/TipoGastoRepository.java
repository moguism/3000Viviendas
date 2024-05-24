package com.macauris.gestionComunitaria.repositories;

import com.macauris.gestionComunitaria.models.TipoGasto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoGastoRepository extends JpaRepository<TipoGasto, Long> {
    
}
