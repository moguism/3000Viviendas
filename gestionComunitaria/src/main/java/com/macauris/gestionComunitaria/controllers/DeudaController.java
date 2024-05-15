package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Deuda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.DeudaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
@RequestMapping("/api/deudas")
public class DeudaController {
    
    @Autowired    
    private DeudaRepository deudaRepository; 

    @GetMapping
    public List<Deuda> getAllDeudas(){

        return deudaRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Deuda> getDeudaById(@PathVariable Long id) {
        Optional<Deuda> deuda = deudaRepository.findById(id);
        return deuda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
