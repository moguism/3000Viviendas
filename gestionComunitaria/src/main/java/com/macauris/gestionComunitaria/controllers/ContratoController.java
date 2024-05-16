package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.ContratoRepository;
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
@RequestMapping("/api/contratos")
public class ContratoController {
    
     @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO (para evitar constructores, el "this", etc.)
    private ContratoRepository ContratoRepository; 

    @GetMapping
    public List<Contrato> getAllContratos(){
        return ContratoRepository.findAll();
    }
    
    @GetMapping("/{id}") // Esto es LONG TAILING: si la petición llega de "/api/comunidades/" se ejecuta la primera, pero si va a "/api/comunidades/id" (siendo id un número), va a esta
    public ResponseEntity<Contrato> getContratoById(@PathVariable Long id) {
        Optional<Contrato> contrato = ContratoRepository.findById(id); // "Optional" ES EN CASO DE QUE EXISTA, Y "findById" NO PERMITE FOREIGN KEY
        return contrato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // "buid()" ES PARA CONSTRUIR LA RESPUESTA
    }
    
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Contrato> createContrato(@RequestBody Contrato contrato){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        Contrato savedContrato = ContratoRepository.save(contrato);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContrato);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContrato(@PathVariable Long id){
        
        if(!ContratoRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        ContratoRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Contrato> updateContrato(@PathVariable Long id, @RequestBody Contrato contrato){
        
        if(!ContratoRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        contrato.setId(id);
        Contrato savedContrato = ContratoRepository.save(contrato); // EN EL "save" HA ACTUALIZADO
        
        return ResponseEntity.ok(savedContrato);
                
    }
    
    // EL VERBO "Patch" ES PARA ACTUALIZAR PARCIALMENTE
    
}
