package com.macauris.gestionComunitaria.repositories;

import com.macauris.gestionComunitaria.models.Vecino;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VecinoRepository extends JpaRepository<Vecino, Long> {
    
}
