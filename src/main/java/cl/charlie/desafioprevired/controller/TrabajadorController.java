/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.controller;

import cl.charlie.desafioprevired.model.Trabajador;
import cl.charlie.desafioprevired.service.TrabajadorService;
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
 * Controlador REST para la gestión de Trabajador
 *
 * @author cabra
 * @version 27122024
 */
@RestController
@RequestMapping("/api/trabajadores")
public class TrabajadorController {
    @Autowired
    private TrabajadorService trabajadorService;

    @PostMapping
    public ResponseEntity<Trabajador> crearTrabajador(@RequestBody Trabajador trabajador) {
        return ResponseEntity.ok(trabajadorService.crearTrabajador(trabajador));
    }

    @GetMapping
    public ResponseEntity<List<Trabajador>> obtenerTodosLosTrabajadores() {
        return ResponseEntity.ok(trabajadorService.obtenerTodosLosTrabajadores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trabajador> obtenerTrabajadorPorId(@PathVariable Long id) {
        return ResponseEntity.ok(trabajadorService.obtenerTrabajadorPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trabajador> actualizarTrabajador(@PathVariable Long id, @RequestBody Trabajador trabajador) {
        return ResponseEntity.ok(trabajadorService.actualizarTrabajador(id, trabajador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTrabajador(@PathVariable Long id) {
        trabajadorService.eliminarTrabajador(id);
        return ResponseEntity.ok().build();
    }
}
