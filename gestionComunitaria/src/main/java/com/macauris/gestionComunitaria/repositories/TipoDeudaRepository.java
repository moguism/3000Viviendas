package com.macauris.gestionComunitaria.repositories;

import com.macauris.gestionComunitaria.models.TipoDeuda;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoDeudaRepository extends JpaRepository<TipoDeuda, Long> {
    
}
