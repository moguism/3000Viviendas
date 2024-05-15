package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Reunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.ReunionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
@RequestMapping("/api/reuniones")
public class ReunionController {

    @Autowired
    private ReunionRepository reunionRepository;

    @GetMapping
    public List<Reunion> getAllReuniones(){

        return reunionRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Reunion> getReunionById(@PathVariable Long id) {
        Optional<Reunion> reunion = reunionRepository.findById(id);
        return reunion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
