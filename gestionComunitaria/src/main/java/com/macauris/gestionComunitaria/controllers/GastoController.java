package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Gasto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.GastoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/gastos")
public class GastoController {
    
    @Autowired    
    private GastoRepository gastoRepository; 

    @GetMapping
    public List<Gasto> getAllGastos(){

        return gastoRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Gasto> getGastoById(@PathVariable Long id) {
        Optional<Gasto> gasto = gastoRepository.findById(id);
        return gasto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
