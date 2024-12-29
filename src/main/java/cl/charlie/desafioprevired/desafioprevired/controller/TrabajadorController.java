/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.desafioprevired.controller;

import cl.charlie.desafioprevired.desafioprevired.model.Trabajador;
import cl.charlie.desafioprevired.desafioprevired.service.TrabajadorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cabra
 */
@RestController
@RequestMapping("/api/empresas/{empresaId}/trabajadores")
public class TrabajadorController {
    @Autowired
    private TrabajadorService trabajadorService;
    
    

    /*@GetMapping
    public List<Trabajador> getAllTrabajadores() {
        return trabajadorService.getAllTrabajadores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trabajador> getTrabajadorById(@PathVariable Long id) {
        return trabajadorService.getTrabajadorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Trabajador createTrabajador(@RequestBody Trabajador trabajador) {
        return trabajadorService.saveTrabajador(trabajador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trabajador> updateTrabajador(@PathVariable Long id, @RequestBody Trabajador trabajador) {
        return trabajadorService.getTrabajadorById(id)
                .map(existingTrabajador -> {
                    trabajador.setId(existingTrabajador.getId());
                    return ResponseEntity.ok(trabajadorService.saveTrabajador(trabajador));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrabajador(@PathVariable Long id) {
        trabajadorService.deleteTrabajador(id);
        return ResponseEntity.noContent().build();
    }*/
}
