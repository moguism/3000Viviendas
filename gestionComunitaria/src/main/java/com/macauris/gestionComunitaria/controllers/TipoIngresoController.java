package com.macauris.gestionComunitaria.controllers;
import com.macauris.gestionComunitaria.models.TipoIngreso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.TipoIngresoRepository;
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
@RequestMapping("/api/tipos-ingreso")
public class TipoIngresoController {

    @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO (para evitar constructores, el "this", etc.)
    private TipoIngresoRepository tipoIngresoRepository; 

    @GetMapping
    public List<TipoIngreso> getAllTiposIngreso(){
        return tipoIngresoRepository.findAll();
    }
    
    @GetMapping("/{id}") // Esto es LONG TAILING: si la petición llega de "/api/tipos-ingreso/" se ejecuta la primera, pero si va a "/api/tipos-ingreso/id" (siendo id un número), va a esta
    public ResponseEntity<TipoIngreso> getTipoIngresoById(@PathVariable Long id) {
        Optional<TipoIngreso> tipoIngreso = tipoIngresoRepository.findById(id); // "Optional" ES EN CASO DE QUE EXISTA, Y "findById" NO PERMITE FOREIGN KEY
        return tipoIngreso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); // "build()" ES PARA CONSTRUIR LA RESPUESTA
    }
    
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<TipoIngreso> createTipoIngreso(@RequestBody TipoIngreso tipoIngreso){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        TipoIngreso savedTipoIngreso = tipoIngresoRepository.save(tipoIngreso);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTipoIngreso);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoIngreso(@PathVariable Long id){
        
        if(!tipoIngresoRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        tipoIngresoRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TipoIngreso> updateTipoIngreso(@PathVariable Long id, @RequestBody TipoIngreso tipoIngreso){
        
        if(!tipoIngresoRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        tipoIngreso.setId(id);
        TipoIngreso savedTipoIngreso = tipoIngresoRepository.save(tipoIngreso); // EN EL "save" HA ACTUALIZADO
        
        return ResponseEntity.ok(savedTipoIngreso);
                
    }
    
    // EL VERBO "Patch" ES PARA ACTUALIZAR PARCIALMENTE
    
}
