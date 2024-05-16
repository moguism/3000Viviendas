package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.EventoRepository;
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
@RequestMapping("/api/eventos")
public class EventoController {
    
    @Autowired    
    private EventoRepository eventoRepository; 

    @GetMapping
    public List<Evento> getAllEventos(){

        return eventoRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventoById(@PathVariable Long id) {
        Optional<Evento> evento = eventoRepository.findById(id);
        return evento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        Evento savedEvento = eventoRepository.save(evento);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvento);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id){
        
        if(!eventoRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        eventoRepository.deleteById(id);
        
        return ResponseEntity.noContent().build();
        
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Evento> updateEvento(@PathVariable Long id, @RequestBody Evento evento){
        
        if(!eventoRepository.existsById(id)){
            
            return ResponseEntity.notFound().build();
            
        }
        
        evento.setId(id);
        Evento savedEvento = eventoRepository.save(evento); // EN EL "save" HA ACTUALIZADO
        
        return ResponseEntity.ok(savedEvento);
                
    }
}
