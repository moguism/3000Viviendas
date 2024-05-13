package com.macauris.gestionComunitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.macauris.gestionComunitaria.models.Contratante;


public interface ContratanteRepository extends JpaRepository<Contratante, Long>{
    
}
