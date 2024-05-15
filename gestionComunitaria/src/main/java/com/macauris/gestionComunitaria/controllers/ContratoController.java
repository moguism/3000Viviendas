package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.ContratoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
@RequestMapping("/api/contratos")
public class ContratoController {
    
    @Autowired    
    private ContratoRepository contratoRepository; 

    @GetMapping
    public List<Contrato> getAllContratos(){

        return contratoRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Contrato> getContratoById(@PathVariable Long id) {
        Optional<Contrato> contrato = contratoRepository.findById(id);
        return contrato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
