package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Vecino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.VecinoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/vectorinos")
public class VecinoController {
    
    @Autowired    
    private VecinoRepository vecinoRepository; 

    @GetMapping
    public List<Vecino> getAllVectorinos(){

        return vecinoRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Vecino> getVecinoById(@PathVariable Long id) {
        Optional<Vecino> vecino = vecinoRepository.findById(id);
        return vecino.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}

