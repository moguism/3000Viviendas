package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Comunidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macauris.gestionComunitaria.repositories.ComunidadRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

// PARA QUE ATIENDAN PETICIONES (en este caso, en la dirección establecida, que es un ENDPOINT)
@RestController
@RequestMapping("/api/comunidades")
public class ComunidadController {

    @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO (para evitar constructores, el "this", etc.)
    private ComunidadRepository comunidadRepository; 

    @GetMapping
    public List<Comunidad> getAllComunidades(){

        return comunidadRepository.findAll();
        
    }
    
    @GetMapping("/{id}") // Esto es LONG TAILING: si la petición llega de "/api/comunidades/" se ejecuta la primera, pero si va a "/api/comunidades/id" (siendo id un número), va a esta
    public ResponseEntity<Comunidad> getComunidadById(@PathVariable Long id) {
        Optional<Comunidad> comunidad = comunidadRepository.findById(id); // "Optional" ES EN CASO DE QUE EXISTA, Y "findById" NO PERMITE FOREIGN KEY
        return comunidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
