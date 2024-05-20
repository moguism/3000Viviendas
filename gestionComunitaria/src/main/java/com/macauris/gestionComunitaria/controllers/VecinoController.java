package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Vecino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.VecinoRepository;
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
@RequestMapping("/api/vecinos")
public class VecinoController {

    @Autowired
    private VecinoRepository vecinoRepository;

    @GetMapping
    public List<Vecino> getAllVectorinos() {

        return vecinoRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Vecino> getVecinoById(@PathVariable Long id) {
        Optional<Vecino> vecino = vecinoRepository.findById(id);
        return vecino.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Vecino> createVecino(@RequestBody Vecino vecino) {
        Vecino savedVecino = vecinoRepository.save(vecino);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVecino);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVecino(@PathVariable Long id) {

        if (!vecinoRepository.existsById(id)) {

            return ResponseEntity.notFound().build();

        }

        vecinoRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Vecino> updateVecino(@PathVariable Long id, @RequestBody Vecino vecino) {

        if (!vecinoRepository.existsById(id)) {

            return ResponseEntity.notFound().build();

        }

        vecino.setId(id);
        Vecino savedVecino = vecinoRepository.save(vecino); // EN EL "save" HA ACTUALIZADO

        return ResponseEntity.ok(savedVecino);

    }

}
