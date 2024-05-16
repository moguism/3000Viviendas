package com.macauris.gestionComunitaria.controllers;

import com.macauris.gestionComunitaria.models.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.LocalRepository;
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
@RequestMapping("/api/locales")
public class LocalController {
    
    @Autowired    
    private LocalRepository localRepository; 

    @GetMapping
    public List<Local> getAllLocales(){

        return localRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Local> getLocalById(@PathVariable Long id) {
        Optional<Local> local = localRepository.findById(id);
        return local.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
       }
    @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Local> createLocal(@RequestBody Local local){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        Local savedLocal = localRepository.save(local);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLocal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocal(@PathVariable Long id){

        if(!localRepository.existsById(id)){

            return ResponseEntity.notFound().build();

        }

        localRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> updateLocal(@PathVariable Long id, @RequestBody Local local){

        if(!localRepository.existsById(id)){

            return ResponseEntity.notFound().build();

        }

        local.setId(id);
        Local savedLocal = localRepository.save(local); // EN EL "save" HA ACTUALIZADO

        return ResponseEntity.ok(savedLocal);

    }

}
