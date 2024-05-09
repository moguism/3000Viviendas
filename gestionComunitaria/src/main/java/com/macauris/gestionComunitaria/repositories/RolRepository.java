package com.macauris.gestionComunitaria.repositories;

import com.macauris.gestionComunitaria.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolRepository extends JpaRepository<Rol, Long> {
    
}