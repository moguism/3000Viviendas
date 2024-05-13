package com.macauris.gestionComunitaria.controllers;
import com.macauris.gestionComunitaria.models.TipoDeuda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.TipoDeudaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/tipoDeudas")
public class TipoDeudaController {
   
    @Autowired    
    private TipoDeudaRepository tipoDeudaRepository; 

    @GetMapping
    public List<TipoDeuda> getAllTipoDeudas(){

        return tipoDeudaRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TipoDeuda> getTipoDeudaById(@PathVariable Long id) {
        Optional<TipoDeuda> tipoDeuda = tipoDeudaRepository.findById(id);
        return tipoDeuda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}