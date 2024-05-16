package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Reunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.ReunionRepository;
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
@RequestMapping("/api/reuniones")
public class ReunionController {

    @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO (para evitar constructores, el "this", etc.)
    private ReunionRepository reunionRepository; 

    @GetMapping
    public List<Reunion> getAllReuniones(){
        return reunionRepository.findAll();
    }
    
    @GetMapping("/{id}") // Esto es LONG TAILING: si la petición llega de "/api/reuniones/" se ejecuta la primera, pero si va a "/api/reuniones/id" (siendo id un número), va a esta
    public ResponseEntity<Reunion> getReunionById(@PathVariable Long id) {
        Optional<Reunion> reunion = reunionRepository.findById(id); // "Optional" ES EN CASO DE QUE EXISTA, Y "findById" NO PERMITE FOREIGN KEY
        return reunion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // "buid()" ES PARA CONSTRUIR LA RESPUESTA
    }
    
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Reunion> createReunion(@RequestBody Reunion reunion){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        Reunion savedReunion = reunionRepository.save(reunion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReunion);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReunion(@PathVariable Long id){
        
        if(!reunionRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        reunionRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Reunion> updateReunion(@PathVariable Long id, @RequestBody Reunion reunion){
        
        if(!reunionRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        reunion.setId(id);
        Reunion savedReunion = reunionRepository.save(reunion); // EN EL "save" HA ACTUALIZADO
        
        return ResponseEntity.ok(savedReunion);
                
    }
    
    // EL VERBO "Patch" ES PARA ACTUALIZAR PARCIALMENTE
    
}
