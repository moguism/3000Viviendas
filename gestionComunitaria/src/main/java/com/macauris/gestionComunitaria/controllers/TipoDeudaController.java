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
@RequestMapping("/api/tipos-deuda")
public class TipoDeudaController {

    @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO (para evitar constructores, el "this", etc.)
    private TipoDeudaRepository tipoDeudaRepository; 

    @GetMapping
    public List<TipoDeuda> getAllTiposDeuda(){
        return tipoDeudaRepository.findAll();
    }
    
    @GetMapping("/{id}") // Esto es LONG TAILING: si la petición llega de "/api/tipos-deuda/" se ejecuta la primera, pero si va a "/api/tipos-deuda/id" (siendo id un número), va a esta
    public ResponseEntity<TipoDeuda> getTipoDeudaById(@PathVariable Long id) {
        Optional<TipoDeuda> tipoDeuda = tipoDeudaRepository.findById(id); // "Optional" ES EN CASO DE QUE EXISTA, Y "findById" NO PERMITE FOREIGN KEY
        return tipoDeuda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // "build()" ES PARA CONSTRUIR LA RESPUESTA
    }
    
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<TipoDeuda> createTipoDeuda(@RequestBody TipoDeuda tipoDeuda){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        TipoDeuda savedTipoDeuda = tipoDeudaRepository.save(tipoDeuda);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTipoDeuda);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoDeuda(@PathVariable Long id){
        
        if(!tipoDeudaRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        tipoDeudaRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TipoDeuda> updateTipoDeuda(@PathVariable Long id, @RequestBody TipoDeuda tipoDeuda){
        
        if(!tipoDeudaRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        tipoDeuda.setId(id);
        TipoDeuda savedTipoDeuda = tipoDeudaRepository.save(tipoDeuda); // EN EL "save" HA ACTUALIZADO
        
        return ResponseEntity.ok(savedTipoDeuda);
                
    }
    
    // EL VERBO "Patch" ES PARA ACTUALIZAR PARCIALMENTE
    
}
