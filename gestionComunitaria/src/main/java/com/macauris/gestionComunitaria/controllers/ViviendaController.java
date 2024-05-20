package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Vivienda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.ViviendaRepository;
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
@RequestMapping("/api/viviendas")
public class ViviendaController {

    @Autowired
    private ViviendaRepository viviendaRepository;

    @GetMapping
    public List<Vivienda> getAllViviendas() {

        return viviendaRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Vivienda> getViviendaById(@PathVariable Long id) {
        Optional<Vivienda> vivienda = viviendaRepository.findById(id);
        return vivienda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Vivienda> createVivienda(@RequestBody Vivienda vivienda) {
        Vivienda savedVivienda = viviendaRepository.save(vivienda);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVivienda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVivienda(@PathVariable Long id) {

        if (!viviendaRepository.existsById(id)) {

            return ResponseEntity.notFound().build();

        }

        viviendaRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Vivienda> updateVivienda(@PathVariable Long id, @RequestBody Vivienda vivienda) {

        if (!viviendaRepository.existsById(id)) {

            return ResponseEntity.notFound().build();

        }

        vivienda.setId(id);
        Vivienda savedVivienda = viviendaRepository.save(vivienda); // EN EL "save" HA ACTUALIZADO

        return ResponseEntity.ok(savedVivienda);

    }

}
