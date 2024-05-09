package com.macauris.gestionComunitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.macauris.gestionComunitaria.models.Contratante;


public interface ContratanteRespository extends JpaRepository<Contratante, Long>{
    
}
