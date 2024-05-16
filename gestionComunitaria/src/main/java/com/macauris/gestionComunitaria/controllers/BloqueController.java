package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Bloque;
import com.macauris.gestionComunitaria.repositories.BloqueRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
@RequestMapping("/api/bloques")
public class BloqueController {
    
    @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO (para evitar constructores, el "this", etc.)
    private BloqueRepository BloqueRepository; 

    @GetMapping
    public List<Bloque> getAllBloques(){
        return BloqueRepository.findAll();
    }
    
    @GetMapping("/{id}") // Esto es LONG TAILING: si la petición llega de "/api/comunidades/" se ejecuta la primera, pero si va a "/api/comunidades/id" (siendo id un número), va a esta
    public ResponseEntity<Bloque> getBloqueById(@PathVariable Long id) {
        Optional<Bloque> Bloque = BloqueRepository.findById(id); // "Optional" ES EN CASO DE QUE EXISTA, Y "findById" NO PERMITE FOREIGN KEY
        return Bloque.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // "buid()" ES PARA CONSTRUIR LA RESPUESTA
    }
    
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Bloque> createBloque(@RequestBody Bloque bloque){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        Bloque savedBloque = BloqueRepository.save(bloque);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBloque);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloque(@PathVariable Long id){
        
        if(!BloqueRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        BloqueRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Bloque> updateBloque(@PathVariable Long id, @RequestBody Bloque bloque){
        
        if(!BloqueRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        bloque.setId(id);
        Bloque savedBloque = BloqueRepository.save(bloque); // EN EL "save" HA ACTUALIZADO
        
        return ResponseEntity.ok(savedBloque);
                
    }
    
    // EL VERBO "Patch" ES PARA ACTUALIZAR PARCIALMENTE
    
}
