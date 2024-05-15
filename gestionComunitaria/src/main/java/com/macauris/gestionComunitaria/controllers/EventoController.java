package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Evento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.EventoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

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
}
