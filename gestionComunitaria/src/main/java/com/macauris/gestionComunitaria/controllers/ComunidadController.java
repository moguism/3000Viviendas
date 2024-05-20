package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Comunidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.ComunidadRepository;
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

// PARA QUE ATIENDAN PETICIONES (en este caso, en la dirección establecida, que es un ENDPOINT)
@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
@RequestMapping("/api/comunidades")
public class ComunidadController {

    @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO (para evitar constructores, el "this", etc.)
    private ComunidadRepository comunidadRepository; 

    @GetMapping
    public List<Comunidad> getAllComunidades(){
        return comunidadRepository.findAll();
    }
    
    @GetMapping("/{id}") // Esto es LONG TAILING: si la petición llega de "/api/comunidades/" se ejecuta la primera, pero si va a "/api/comunidades/id" (siendo id un número), va a esta
    public ResponseEntity<Comunidad> getComunidadById(@PathVariable Long id) {
        Optional<Comunidad> comunidad = comunidadRepository.findById(id); // "Optional" ES EN CASO DE QUE EXISTA, Y "findById" NO PERMITE FOREIGN KEY
        return comunidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // "buid()" ES PARA CONSTRUIR LA RESPUESTA
    }
    
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Comunidad> createComunidad(@RequestBody Comunidad comunidad){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        Comunidad savedComunidad = comunidadRepository.save(comunidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComunidad);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComunidad(@PathVariable Long id){
        
        if(!comunidadRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        comunidadRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Comunidad> updateComunidad(@PathVariable Long id, @RequestBody Comunidad comunidad){
        
        if(!comunidadRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        comunidad.setId(id);
        Comunidad savedComunidad = comunidadRepository.save(comunidad); // EN EL "save" HA ACTUALIZADO
        
        return ResponseEntity.ok(savedComunidad);
                
    }
    
    // EL VERBO "Patch" ES PARA ACTUALIZAR PARCIALMENTE
    
}
