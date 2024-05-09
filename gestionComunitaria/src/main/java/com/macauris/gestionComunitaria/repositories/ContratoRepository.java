package com.macauris.gestionComunitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.macauris.gestionComunitaria.models.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long>{
    
}
