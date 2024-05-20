package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Contratante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.ContratanteRepository;
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
@RequestMapping("/api/contratantes")
public class ContratanteController {
    
     @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO (para evitar constructores, el "this", etc.)
    private ContratanteRepository ContratanteRepository; 

    @GetMapping
    public List<Contratante> getAllContratante(){
        return ContratanteRepository.findAll();
    }
    
    @GetMapping("/{id}") // Esto es LONG TAILING: si la petición llega de "/api/comunidades/" se ejecuta la primera, pero si va a "/api/comunidades/id" (siendo id un número), va a esta
    public ResponseEntity<Contratante> getContratanteById(@PathVariable Long id) {
        Optional<Contratante> contratante = ContratanteRepository.findById(id); // "Optional" ES EN CASO DE QUE EXISTA, Y "findById" NO PERMITE FOREIGN KEY
        return contratante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // "buid()" ES PARA CONSTRUIR LA RESPUESTA
    }
    
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Contratante> createContratante(@RequestBody Contratante contratante){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        Contratante savedContratante = ContratanteRepository.save(contratante);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContratante);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContratante(@PathVariable Long id){
        
        if(!ContratanteRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        ContratanteRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Contratante> updateContratante(@PathVariable Long id, @RequestBody Contratante contratante){
        
        if(!ContratanteRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        contratante.setId(id);
        Contratante savedContratante = ContratanteRepository.save(contratante); // EN EL "save" HA ACTUALIZADO
        
        return ResponseEntity.ok(savedContratante);
                
    }
    
    // EL VERBO "Patch" ES PARA ACTUALIZAR PARCIALMENTE
    
}
