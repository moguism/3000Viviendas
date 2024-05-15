package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Mensualidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.MensualidadRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
@RequestMapping("/api/mensualidades")
public class MensualidadController {
    
    @Autowired    
    private MensualidadRepository mensualidadRepository; 

    @GetMapping
    public List<Mensualidad> getAllMensualidades(){

        return mensualidadRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Mensualidad> getMensualidadById(@PathVariable Long id) {
        Optional<Mensualidad> mensualidad = mensualidadRepository.findById(id);
        return mensualidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
