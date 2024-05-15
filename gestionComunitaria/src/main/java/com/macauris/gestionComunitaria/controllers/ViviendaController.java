package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Vivienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.ViviendaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
@RequestMapping("/api/viviendas")
public class ViviendaController {
    
    @Autowired    
    private ViviendaRepository viviendaRepository; 

    @GetMapping
    public List<Vivienda> getAllViviendas(){

        return viviendaRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Vivienda> getViviendaById(@PathVariable Long id) {
        Optional<Vivienda> vivienda = viviendaRepository.findById(id);
        return vivienda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
