package com.macauris.gestionComunitaria.controllers;
import com.macauris.gestionComunitaria.models.Ingreso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.IngresoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/ingresos")
public class IngresoController {
   
    @Autowired    
    private IngresoRepository ingresoRepository; 

    @GetMapping
    public List<Ingreso> getAllIngresos(){

        return ingresoRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ingreso> getIngresoById(@PathVariable Long id) {
        Optional<Ingreso> ingreso = ingresoRepository.findById(id);
        return ingreso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
