package com.macauris.gestionComunitaria.repositories;

import com.macauris.gestionComunitaria.models.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReunionRepository extends JpaRepository<Reunion, Long> {
    
}