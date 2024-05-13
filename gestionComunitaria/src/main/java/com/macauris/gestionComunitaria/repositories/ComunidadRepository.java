package com.macauris.gestionComunitaria.repositories;

import com.macauris.gestionComunitaria.models.Comunidad;
import org.springframework.data.jpa.repository.JpaRepository;

// Los repositories se utilizan para tener todos los métodos que la clase necesita (por ejemplo, crear una comunidad, borrarla, listarla, etc.)
// "Long" es el tipo de dato del Id
// Al extender JpaRepository heredamos todos los métodos necesarios
public interface ComunidadRepository extends JpaRepository<Comunidad, Long> {
    
}
