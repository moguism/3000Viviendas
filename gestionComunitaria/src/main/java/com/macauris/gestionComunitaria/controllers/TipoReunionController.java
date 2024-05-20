package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.TipoReunion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.TipoReunionRepository;
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
@RequestMapping("/api/tipos-reunion")
public class TipoReunionController {

    @Autowired
    private TipoReunionRepository tipoReunionRepository;

    @GetMapping
    public List<TipoReunion> getAllTipoReuniones() {

        return tipoReunionRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoReunion> getTipoReunionById(@PathVariable Long id) {
        Optional<TipoReunion> tipoReunion = tipoReunionRepository.findById(id);
        return tipoReunion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<TipoReunion> createTipoReunion(@RequestBody TipoReunion tipoReunion) { 
        TipoReunion savedTipoReunion = tipoReunionRepository.save(tipoReunion);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTipoReunion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoReunion(@PathVariable Long id) {

        if (!tipoReunionRepository.existsById(id)) {

            return ResponseEntity.notFound().build();

        }

        tipoReunionRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoReunion> updateTipoReunion(@PathVariable Long id, @RequestBody TipoReunion tipoReunion) {

        if (!tipoReunionRepository.existsById(id)) {

            return ResponseEntity.notFound().build();

        }

        tipoReunion.setId(id);
        TipoReunion savedTipoReunion = tipoReunionRepository.save(tipoReunion); // EN EL "save" HA ACTUALIZADO

        return ResponseEntity.ok(savedTipoReunion);

    }

}
