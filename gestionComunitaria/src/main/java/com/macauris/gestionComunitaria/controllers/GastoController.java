package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Gasto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.GastoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
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
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Gasto> createGasto(@RequestBody Gasto gasto){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        Gasto savedGasto = gastoRepository.save(gasto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGasto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGasto(@PathVariable Long id){

        if(!gastoRepository.existsById(id)){

            return ResponseEntity.notFound().build();

        }

        gastoRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Gasto> updateGasto(@PathVariable Long id, @RequestBody Gasto gasto){

        if(!gastoRepository.existsById(id)){

            return ResponseEntity.notFound().build();

        }

        gasto.setId(id);
        Gasto savedGasto = gastoRepository.save(gasto); // EN EL "save" HA ACTUALIZADO

        return ResponseEntity.ok(savedGasto);

    }

    
}
