package com.macauris.gestionComunitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.macauris.gestionComunitaria.models.Comision;

public interface ComisionRepository extends JpaRepository<Comision, Long>  {
    
}
