package com.macauris.gestionComunitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.macauris.gestionComunitaria.models.Bloque;


public interface BloqueRespository extends JpaRepository<Bloque, Long>{
    
}
