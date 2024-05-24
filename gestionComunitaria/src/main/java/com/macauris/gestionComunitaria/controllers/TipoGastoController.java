package com.macauris.gestionComunitaria.controllers;
import com.macauris.gestionComunitaria.models.TipoGasto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.TipoGastoRepository;
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
@RequestMapping("/api/tipos-gasto")
public class TipoGastoController {

    @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO (para evitar constructores, el "this", etc.)
    private TipoGastoRepository tipoGastoRepository; 

    @GetMapping
    public List<TipoGasto> getAllTiposGasto(){
        return tipoGastoRepository.findAll();
    }
    
    @GetMapping("/{id}") // Esto es LONG TAILING: si la petición llega de "/api/tipos-gasto/" se ejecuta la primera, pero si va a "/api/tipos-gasto/id" (siendo id un número), va a esta
    public ResponseEntity<TipoGasto> getTipoGastoById(@PathVariable Long id) {
        Optional<TipoGasto> tipoGasto = tipoGastoRepository.findById(id); // "Optional" ES EN CASO DE QUE EXISTA, Y "findById" NO PERMITE FOREIGN KEY
        return tipoGasto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // "build()" ES PARA CONSTRUIR LA RESPUESTA
    }
    
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<TipoGasto> createTipoGasto(@RequestBody TipoGasto tipoGasto){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        TipoGasto savedTipoGasto = tipoGastoRepository.save(tipoGasto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTipoGasto);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoGasto(@PathVariable Long id){
        
        if(!tipoGastoRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        tipoGastoRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TipoGasto> updateTipoGasto(@PathVariable Long id, @RequestBody TipoGasto tipoGasto){
        
        if(!tipoGastoRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        tipoGasto.setId(id);
        TipoGasto savedTipoGasto = tipoGastoRepository.save(tipoGasto); // EN EL "save" HA ACTUALIZADO
        
        return ResponseEntity.ok(savedTipoGasto);
                
    }
    
    // EL VERBO "Patch" ES PARA ACTUALIZAR PARCIALMENTE
    
}
