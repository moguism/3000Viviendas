package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.TipoReunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.TipoReunionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
@RequestMapping("/api/tipoReuniones")
public class TipoReunionController {
    
    @Autowired    
    private TipoReunionRepository tipoReunionRepository; 

    @GetMapping
    public List<TipoReunion> getAllTipoReuniones(){

        return tipoReunionRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TipoReunion> getTipoReunionById(@PathVariable Long id) {
        Optional<TipoReunion> tipoReunion = tipoReunionRepository.findById(id);
        return tipoReunion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
