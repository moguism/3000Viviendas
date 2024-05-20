package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Comision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.ComisionRepository;
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
@RequestMapping("/api/comisiones")
public class ComisionController {
    
     @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO (para evitar constructores, el "this", etc.)
    private ComisionRepository ComisionRepository; 

    @GetMapping
    public List<Comision> getAllComisiones(){
        return ComisionRepository.findAll();
    }
    
    @GetMapping("/{id}") // Esto es LONG TAILING: si la petición llega de "/api/comunidades/" se ejecuta la primera, pero si va a "/api/comunidades/id" (siendo id un número), va a esta
    public ResponseEntity<Comision> getComisionById(@PathVariable Long id) {
        Optional<Comision> comision = ComisionRepository.findById(id); // "Optional" ES EN CASO DE QUE EXISTA, Y "findById" NO PERMITE FOREIGN KEY
        return comision.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // "buid()" ES PARA CONSTRUIR LA RESPUESTA
    }
    
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Comision> createComision(@RequestBody Comision comision){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        Comision savedComunidad = ComisionRepository.save(comision);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedComunidad);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComunidad(@PathVariable Long id){
        
        if(!ComisionRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        ComisionRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Comision> updateComunidad(@PathVariable Long id, @RequestBody Comision comision){
        
        if(!ComisionRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        comision.setId(id);
        Comision savedComunidad = ComisionRepository.save(comision); // EN EL "save" HA ACTUALIZADO
        
        return ResponseEntity.ok(savedComunidad);
                
    }
    
    // EL VERBO "Patch" ES PARA ACTUALIZAR PARCIALMENTE
    
}
