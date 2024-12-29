/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.service;

import cl.charlie.desafioprevired.model.Empresa;
import cl.charlie.desafioprevired.model.Trabajador;
import cl.charlie.desafioprevired.repository.EmpresaRepository;
import cl.charlie.desafioprevired.repository.TrabajadorRepository;
import cl.charlie.desafioprevired.utils.RutValidator;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase de servicios de Trabajador
 *
 * @author cabra
 * @version 27122024
 */
@Service
public class TrabajadorService {
    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public Trabajador saveTrabajador(Long empresaId, Trabajador trabajador) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + empresaId));

        // Validar RUT único
        if (trabajadorRepository.existsByRut(trabajador.getRut())) {
            throw new RuntimeException("Ya existe un trabajador con el RUT: " + trabajador.getRut());
        }

        trabajador.setEmpresa(empresa);
        return trabajadorRepository.save(trabajador);
    }
    
    public Trabajador findById(Long id) {
        return trabajadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado con ID: " + id));
    }

    public List<Trabajador> findByEmpresaId(Long empresaId) {
        return trabajadorRepository.findByEmpresaId(empresaId);
    }
    
    @Transactional
    public Trabajador updateTrabajador(Long id, Trabajador trabajadorDetails) {
        Trabajador trabajador = findById(id);
        
        // Validar que si el RUT cambió, no exista otro trabajador con ese RUT
        if (!trabajador.getRut().equals(trabajadorDetails.getRut()) && 
            trabajadorRepository.existsByRut(trabajadorDetails.getRut())) {
            throw new RuntimeException("Ya existe un trabajador con el RUT: " + trabajadorDetails.getRut());
        }

        // Actualizar campos
        trabajador.setRut(trabajadorDetails.getRut());
        trabajador.setNombre(trabajadorDetails.getNombre());
        trabajador.setApellidoPaterno(trabajadorDetails.getApellidoPaterno());
        trabajador.setApellidoMaterno(trabajadorDetails.getApellidoMaterno());
        trabajador.setDireccion(trabajadorDetails.getDireccion());

        return trabajadorRepository.save(trabajador);
    }

    @Transactional
    public void deleteTrabajador(Long id) {
        /*if (!trabajadorRepository.existsById(id)) {
            throw new RuntimeException("Trabajador no encontrado con ID: " + id);
        }*/
        trabajadorRepository.deleteById(id);
    }
    
}
