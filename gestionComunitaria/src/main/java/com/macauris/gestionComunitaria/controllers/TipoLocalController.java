package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.TipoLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.TipoLocalRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
@RequestMapping("/api/tipolocales")
public class TipoLocalController {
    
    @Autowired    
    private TipoLocalRepository tipoLocalRepository; 

    @GetMapping
    public List<TipoLocal> getAllTipoLocales(){

        return tipoLocalRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TipoLocal> getTipoLocalById(@PathVariable Long id) {
        Optional<TipoLocal> tipoLocal = tipoLocalRepository.findById(id);
        return tipoLocal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
