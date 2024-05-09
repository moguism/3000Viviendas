package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Comunidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macauris.gestionComunitaria.repositories.ComunidadRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

// PARA QUE ATIENDAN PETICIONES (en este caso, en la dirección establecida)
@RestController
@RequestMapping("/api/comunidades") 
public class ComunidadController {

    @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO
    private ComunidadRepository comunidadRepository; 

    @GetMapping
    public List<Comunidad> getAllComunidades(){

        return comunidadRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Comunidad> getComunidadById(@PathVariable Long id) {
        Optional<Comunidad> comunidad = comunidadRepository.findById(id);
        return comunidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
