package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Contratante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.ContratanteRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/contratantes")
public class ContratanteController {
    
    @Autowired    
    private ContratanteRepository contratanteRepository; 

    @GetMapping
    public List<Contratante> getAllContratantes(){

        return contratanteRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Contratante> getComisionById(@PathVariable Long id) {
        Optional<Contratante> contratante = contratanteRepository.findById(id);
        return contratante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
