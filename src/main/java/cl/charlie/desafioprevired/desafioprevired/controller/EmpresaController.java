/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.desafioprevired.controller;

import cl.charlie.desafioprevired.desafioprevired.model.Empresa;
import cl.charlie.desafioprevired.desafioprevired.model.Trabajador;
import cl.charlie.desafioprevired.desafioprevired.service.EmpresaService;
import cl.charlie.desafioprevired.desafioprevired.service.TrabajadorService;
import cl.charlie.desafioprevired.desafioprevired.utils.RutValidator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Controlador REST para la gestión de Empresas
 *
 * @author cabra
 * @version 27122024
 */

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "http://localhost:3000")
public class EmpresaController {
    
    private final EmpresaService empresaService;
    
    
    /**
     * Constructor para inyeccion
     * @param empresaService logica de negocio
     */        
    @Autowired
    private EmpresaController(EmpresaService empresaService){
        this.empresaService = empresaService;
    };
    
    @Autowired
    private TrabajadorService trabajadorService;
    
    /**
     * Obtener todas las empresas
     * @return ResponseEntity retorna respuesta con o sin informacion
     */
    @GetMapping
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        List<Empresa> empresas = empresaService.getAllEmpresas();
        return empresas.isEmpty() 
            ? ResponseEntity.noContent().build()
            : ResponseEntity.ok(empresas);
    }
    
    

    /**
     * Obtiene una empresa por su ID
     * @param empresaId ID de la empresa a buscar
     * @return ResponseEntity retorna respuesta con o sin informacion
     */
    @GetMapping("/{empresaId}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable Long empresaId) {
        Empresa empresa = empresaService.getEmpresaById(empresaId);
        return empresa == null?
                ResponseEntity.notFound().build()
                : ResponseEntity.ok(empresa);
        
    }
    
    

    /**
     * Crea una nueva empresa
     * @param empresa Datos de la empresa a crear
     * @return ResponseEntity retorna con la empresa creada o error 
     */
    @PostMapping
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        try {
            Empresa newEmpresa = empresaService.createEmpresa(empresa);
            return ResponseEntity.ok(newEmpresa);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    
    /**
     * Actualiza una empresa existente
     * @param empresaId ID de la empresa a actualizar
     * @param empresa Nuevos datos de la empresa
     * @return ResponseEntity retorna con la empresa actualizada o si no existe
     */
    @PutMapping("/{empresaId}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        if (!empresaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        empresa.setId(id);
        Empresa updatedEmpresa = empresaService.updateEmpresa(id, empresa);
        return ResponseEntity.ok(updatedEmpresa);
    }
    
    
    /**
     * Elimina una empresa por su ID
     * @param empresaId ID de la empresa a eliminar
     * @return ResponseEntity retorna sin contenido o si no existe
     */
    @DeleteMapping("/{empresaId}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long empresaId) {
        try {
            
            empresaService.deleteEmpresa(empresaId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{empresaId}/trabajadores")
    public List<Trabajador> getTrabajadoresByEmpresaId(@PathVariable Long empresaId) {
        return trabajadorService.findTrabajadoresByEmpresaId(empresaId);
    }
    
    
    /**
     * Crea un nuevo trabajador a la empresa
     * @param trabajador Datos del trabajador a crear
     * @return ResponseEntity retorna el trabajador creada o error 
     * 
     */
    
    @PostMapping("/{id}/trabajadores")
    public ResponseEntity<Trabajador> addTrabajador(@PathVariable Long id, @RequestBody Trabajador trabajador) {
        try {
            Trabajador newTrabajador = empresaService.addTrabajador(id, trabajador);
            return ResponseEntity.ok(newTrabajador);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
     // Actualizar un trabajador
    @PutMapping("/{empresaId}/trabajadores/{trabajadorId}")
    public ResponseEntity<Trabajador> updateTrabajador(@PathVariable Long empresaId, @PathVariable Long trabajadorId, @RequestBody Trabajador trabajador) {
        if (!empresaService.existsById(empresaId) || !trabajadorService.existsById(trabajadorId)) {
            return ResponseEntity.notFound().build();
        }
        trabajador.setId(trabajadorId);
        trabajador.setEmpresa(empresaService.getEmpresaById(empresaId));
        Trabajador updatedTrabajador = trabajadorService.updateTrabajador(trabajadorId, trabajador);
        return ResponseEntity.ok(updatedTrabajador);
    }

    // Eliminar un trabajador
    @DeleteMapping("/{empresaId}/trabajadores/{trabajadorId}")
    public ResponseEntity<Void> deleteTrabajador(@PathVariable Long empresaId, @PathVariable Long trabajadorId) {
        if (!empresaService.existsById(empresaId) || !trabajadorService.existsById(trabajadorId)) {
            return ResponseEntity.notFound().build();
        }
        trabajadorService.deleteTrabajador(trabajadorId);
        return ResponseEntity.ok().build();
    }

   
}
