package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Comision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.ComisionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
@RequestMapping("/api/comisiones")
public class ComisionController {
    
    @Autowired    
    private ComisionRepository comisionRepository; 

    @GetMapping
    public List<Comision> getAllComisiones(){

        return comisionRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Comision> getComisionById(@PathVariable Long id) {
        Optional<Comision> comision = comisionRepository.findById(id);
        return comision.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
