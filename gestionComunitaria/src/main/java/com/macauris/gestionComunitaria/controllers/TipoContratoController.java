package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.TipoContrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.TipoContratoRepository;
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
@RequestMapping("/api/tipos-contrato")
public class TipoContratoController {

    @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO (para evitar constructores, el "this", etc.)
    private TipoContratoRepository tipoContratoRepository; 

    @GetMapping
    public List<TipoContrato> getAllTiposContrato(){
        return tipoContratoRepository.findAll();
    }
    
    @GetMapping("/{id}") // Esto es LONG TAILING: si la petición llega de "/api/tipos-contrato/" se ejecuta la primera, pero si va a "/api/tipos-contrato/id" (siendo id un número), va a esta
    public ResponseEntity<TipoContrato> getTipoContratoById(@PathVariable Long id) {
        Optional<TipoContrato> tipoContrato = tipoContratoRepository.findById(id); // "Optional" ES EN CASO DE QUE EXISTA, Y "findById" NO PERMITE FOREIGN KEY
        return tipoContrato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // "buid()" ES PARA CONSTRUIR LA RESPUESTA
    }
    
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<TipoContrato> createTipoContrato(@RequestBody TipoContrato tipoContrato){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        TipoContrato savedTipoContrato = tipoContratoRepository.save(tipoContrato);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTipoContrato);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoContrato(@PathVariable Long id){
        
        if(!tipoContratoRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        tipoContratoRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TipoContrato> updateTipoContrato(@PathVariable Long id, @RequestBody TipoContrato tipoContrato){
        
        if(!tipoContratoRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        tipoContrato.setId(id);
        TipoContrato savedTipoContrato = tipoContratoRepository.save(tipoContrato); // EN EL "save" HA ACTUALIZADO
        
        return ResponseEntity.ok(savedTipoContrato);
                
    }
    
    // EL VERBO "Patch" ES PARA ACTUALIZAR PARCIALMENTE
    
}

