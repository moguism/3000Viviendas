package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Bloque;
import com.macauris.gestionComunitaria.repositories.BloqueRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
@RequestMapping("/api/bloques")
public class BloqueController {
    
    @Autowired
    private BloqueRepository bloqueRepository; 

    @GetMapping
    public List<Bloque> getAllBloques(){

        return bloqueRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Bloque> getBloqueById(@PathVariable Long id) {
        Optional<Bloque> bloque = bloqueRepository.findById(id);
        return bloque.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
}
