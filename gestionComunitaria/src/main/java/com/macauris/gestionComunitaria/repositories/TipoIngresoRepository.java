package com.macauris.gestionComunitaria.repositories;

import com.macauris.gestionComunitaria.models.TipoIngreso;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoIngresoRepository extends JpaRepository<TipoIngreso, Long> {
    
}
