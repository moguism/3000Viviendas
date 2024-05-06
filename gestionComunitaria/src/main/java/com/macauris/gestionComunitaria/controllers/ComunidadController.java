package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Comunidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macauris.gestionComunitaria.repositories.ComunidadRepository;
import java.util.List;

// PARA QUE ATIENDAN PETICIONES (en este caso, en la dirección establecida)
@RestController
@RequestMapping("/api/comunidades") 
public class ComunidadController {

    @Autowired // PARA QUE CONECTE DIRECTAMENTE CON LA CLASE QUE CONTROLA EL REPOSITORIO
    private ComunidadRepository comunidadRepository; 

    @GetMapping
    public List<Comunidad> getAllComunidades(){

        return comunidadRepository.findAll();
    }
    
}
