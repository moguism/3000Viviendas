package com.macauris.gestionComunitaria.controllers;
import com.macauris.gestionComunitaria.models.Ingreso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.macauris.gestionComunitaria.repositories.IngresoRepository;
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
@RequestMapping("/api/ingresos")
public class IngresoController {
   
    @Autowired    
    private IngresoRepository ingresoRepository; 

    @GetMapping
    public List<Ingreso> getAllIngresos(){

        return ingresoRepository.findAll();
        
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Ingreso> getIngresoById(@PathVariable Long id) {
        Optional<Ingreso> ingreso = ingresoRepository.findById(id);
        return ingreso.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
        @PostMapping // CUANDO RECIBA UNA PETICIÓN POR POST A LA URL EJECUTA ESTO
    public ResponseEntity<Ingreso> createIngreso(@RequestBody Ingreso ingreso){ // "@RequestBody" LE DICE QUE VA A RECIBIR EL OBJETO A TRAVÉS DEL CUERPO DE LA PETICIÓN
        Ingreso savedIngreso = ingresoRepository.save(ingreso);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIngreso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngreso(@PathVariable Long id){

        if(!ingresoRepository.existsById(id)){

            return ResponseEntity.notFound().build();

        }

        ingresoRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingreso> updateIngreso(@PathVariable Long id, @RequestBody Ingreso ingreso){

        if(!ingresoRepository.existsById(id)){

            return ResponseEntity.notFound().build();

        }

        ingreso.setId(id);
        Ingreso savedIngreso = ingresoRepository.save(ingreso); // EN EL "save" HA ACTUALIZADO

        return ResponseEntity.ok(savedIngreso);

    }

}
