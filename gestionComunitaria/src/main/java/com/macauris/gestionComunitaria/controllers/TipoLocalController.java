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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// PARA QUE ATIENDAN PETICIONES (en este caso, en la dirección establecida, que es un ENDPOINT)
@RestController
@CrossOrigin(origins = "*") // Permitir todos los orígenes
@RequestMapping("/api/tipos-local")
public class TipoLocalController {

    @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO (para evitar constructores, el "this", etc.)
    private TipoLocalRepository tipoLocalRepository; 

    @GetMapping
    public List<TipoLocal> getAllTiposLocal(){
        return tipoLocalRepository.findAll();
    }
    
    @GetMapping("/{id}") // Esto es LONG TAILING: si la petición llega de "/api/tipos-local/" se ejecuta la primera, pero si va a "/api/tipos-local/id" (siendo id un número), va a esta
    public ResponseEntity<TipoLocal> getTipoLocalById(@PathVariable Long id) {
        Optional<TipoLocal> tipoLocal = tipoLocalRepository.findById(id); // "Optional" ES EN CASO DE QUE EXISTA, Y "findById" NO PERMITE FOREIGN KEY
        return tipoLocal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // "build()" ES PARA CONSTRUIR LA RESPUESTA
    }
    
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<TipoLocal> createTipoLocal(@RequestBody TipoLocal tipoLocal){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        TipoLocal savedTipoLocal = tipoLocalRepository.save(tipoLocal);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTipoLocal);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoLocal(@PathVariable Long id){
        
        if(!tipoLocalRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        tipoLocalRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TipoLocal> updateTipoLocal(@PathVariable Long id, @RequestBody TipoLocal tipoLocal){
        
        if(!tipoLocalRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        tipoLocal.setId(id);
        TipoLocal savedTipoLocal = tipoLocalRepository.save(tipoLocal); // EN EL "save" HA ACTUALIZADO
        
        return ResponseEntity.ok(savedTipoLocal);
                
    }
    
    // EL VERBO "Patch" ES PARA ACTUALIZAR PARCIALMENTE
    
}
