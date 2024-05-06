package com.macauris.gestionComunitaria.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.macauris.gestionComunitaria.models.Deuda;

public interface DeudaRepository extends JpaRepository<Deuda, Long>{
    
}
