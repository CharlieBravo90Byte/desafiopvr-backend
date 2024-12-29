/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.controller;

import cl.charlie.desafioprevired.model.Empresa;
import cl.charlie.desafioprevired.model.Trabajador;
import cl.charlie.desafioprevired.service.EmpresaService;
import cl.charlie.desafioprevired.service.TrabajadorService;
import cl.charlie.desafioprevired.utils.RutValidator;
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
    
    
    /**
     * Crea una nueva empresa
     * @param empresa Datos de la empresa a crear
     * @return ResponseEntity retorna con la empresa creada o error 
     */
    @PostMapping
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        try {
            return ResponseEntity.ok(empresaService.crearEmpresa(empresa));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
    
    
    /**
     * Obtener todas las empresas
     * @return ResponseEntity retorna respuesta con o sin informacion
     */
    @GetMapping
    public ResponseEntity<List<Empresa>> obtenerTodasLasEmpresas() {
        //return ResponseEntity.ok(empresaService.obtenerTodasLasEmpresas());
        
        List<Empresa> empresas = empresaService.obtenerTodasLasEmpresas();
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
    public ResponseEntity<Empresa> obtenerEmpresaPorId(@PathVariable Long empresaId) {
        Empresa empresa = empresaService.obtenerEmpresaPorId(empresaId);
        return empresa == null?
                ResponseEntity.notFound().build()
                : ResponseEntity.ok(empresa);
    }
   
    
    /**
     * Actualiza una empresa existente
     * @param empresaId ID de la empresa a actualizar
     * @param empresa Nuevos datos de la empresa
     * @return ResponseEntity retorna con la empresa actualizada o si no existe
     */
    @PutMapping("/{empresaId}")
    public ResponseEntity<Empresa> actualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        return ResponseEntity.ok(empresaService.actualizarEmpresa(id, empresa));
    }

    

     /**
     * Elimina una empresa por su ID
     * @param empresaId ID de la empresa a eliminar
     * @return ResponseEntity retorna sin contenido o si no existe
     */
    @DeleteMapping("/{empresaId}")
    public ResponseEntity<Void> eliminarEmpresa(@PathVariable Long id) {
        try {
            empresaService.eliminarEmpresa(id);
            return ResponseEntity.ok().build();
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
    }
    
    
}
