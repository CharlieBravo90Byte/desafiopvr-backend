/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.controller;

import cl.charlie.desafioprevired.model.Trabajador;
import cl.charlie.desafioprevired.service.TrabajadorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class TrabajadorController {
    @Autowired
    private TrabajadorService trabajadorService;

    @PostMapping("/empresas/{empresaId}/trabajadores")
    public ResponseEntity<?> createTrabajador(@PathVariable Long empresaId, @RequestBody Trabajador trabajador) {
        try {
            Trabajador nuevoTrabajador = trabajadorService.saveTrabajador(empresaId, trabajador);
            return new ResponseEntity<>(nuevoTrabajador, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/empresas/{empresaId}/trabajadores")
    public ResponseEntity<?> getTrabajadoresByEmpresa(@PathVariable Long empresaId) {
        try {
            return new ResponseEntity<>(trabajadorService.findByEmpresaId(empresaId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/trabajadores/{id}")
    public ResponseEntity<?> updateTrabajador(@PathVariable Long id, @RequestBody Trabajador trabajador) {
        try {
            Trabajador updatedTrabajador = trabajadorService.updateTrabajador(id, trabajador);
            return new ResponseEntity<>(updatedTrabajador, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/trabajadores/{Id}")
    public ResponseEntity<?> deleteTrabajador(@PathVariable("id")  Long id) {
        trabajadorService.deleteTrabajador(id);
        return ResponseEntity.noContent().build();
        /*try {
            trabajadorService.deleteTrabajador(id);
            return new ResponseEntity<>("Trabajador eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }*/
    }
}
