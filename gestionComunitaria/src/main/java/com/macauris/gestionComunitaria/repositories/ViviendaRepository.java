package com.macauris.gestionComunitaria.repositories;

import com.macauris.gestionComunitaria.models.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViviendaRepository extends JpaRepository<Vivienda, Long> {
    
}