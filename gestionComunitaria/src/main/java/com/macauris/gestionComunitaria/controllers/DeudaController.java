package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Deuda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.DeudaRepository;
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
@RequestMapping("/api/deudas")
public class DeudaController {

    @Autowired
    private DeudaRepository deudaRepository;

    @GetMapping
    public List<Deuda> getAllDeudas() {

        return deudaRepository.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Deuda> getDeudaById(@PathVariable Long id) {
        Optional<Deuda> deuda = deudaRepository.findById(id);
        return deuda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Deuda> createDeuda(@RequestBody Deuda deuda) {
        Deuda savedDeuda = deudaRepository.save(deuda);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDeuda);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeuda(@PathVariable Long id) {

        if (!deudaRepository.existsById(id)) {

            return ResponseEntity.notFound().build();

        }

        deudaRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Deuda> updateDeuda(@PathVariable Long id, @RequestBody Deuda deuda) {

        if (!deudaRepository.existsById(id)) {

            return ResponseEntity.notFound().build();

        }

        deuda.setId(id);
        Deuda savedDeuda = deudaRepository.save(deuda); // EN EL "save" HA ACTUALIZADO

        return ResponseEntity.ok(savedDeuda);

    }
    
}
