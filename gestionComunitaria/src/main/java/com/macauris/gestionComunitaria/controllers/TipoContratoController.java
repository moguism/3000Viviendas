package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.TipoContrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.TipoContratoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/tipo-contratos")
public class TipoContratoController {
    
    @Autowired    
    private TipoContratoRepository tipoContratoRepository; 

    @GetMapping
    public List<TipoContrato> getAllTiposContratos(){

        return tipoContratoRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TipoContrato> getTipoContratoById(@PathVariable Long id) {
        Optional<TipoContrato> tipoContrato = tipoContratoRepository.findById(id);
        return tipoContrato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
