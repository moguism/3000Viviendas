package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Mensualidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.MensualidadRepository;
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
@RequestMapping("/api/mensualidades")
public class MensualidadController {
    
    @Autowired    
    private MensualidadRepository mensualidadRepository; 

    @GetMapping
    public List<Mensualidad> getAllMensualidades(){

        return mensualidadRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Mensualidad> getMensualidadById(@PathVariable Long id) {
        Optional<Mensualidad> mensualidad = mensualidadRepository.findById(id);
        return mensualidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
        @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Mensualidad> createMensualidad(@RequestBody Mensualidad mensualidad){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        Mensualidad savedMensualidad = mensualidadRepository.save(mensualidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMensualidad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMensualidad(@PathVariable Long id){

        if(!mensualidadRepository.existsById(id)){

            return ResponseEntity.notFound().build();

        }

        mensualidadRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensualidad> updateMensualidad(@PathVariable Long id, @RequestBody Mensualidad mensualidad){

        if(!mensualidadRepository.existsById(id)){

            return ResponseEntity.notFound().build();

        }

        mensualidad.setId(id);
        Mensualidad savedMensualidad = mensualidadRepository.save(mensualidad); // EN EL "save" HA ACTUALIZADO

        return ResponseEntity.ok(savedMensualidad);

    }

}
