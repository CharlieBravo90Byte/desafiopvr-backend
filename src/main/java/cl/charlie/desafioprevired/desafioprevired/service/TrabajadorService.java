/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.desafioprevired.service;

import cl.charlie.desafioprevired.desafioprevired.model.Trabajador;
import cl.charlie.desafioprevired.desafioprevired.repository.TrabajadorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cabra
 */
@Service
public class TrabajadorService {
    @Autowired
    private TrabajadorRepository trabajadorRepository;

    public Trabajador createTrabajador(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }

    public Trabajador updateTrabajador(Long id, Trabajador trabajador) {
        if (!trabajadorRepository.existsById(id)) {
            throw new RuntimeException("Trabajador no encontrado");
        }
        trabajador.setId(id);
        return trabajadorRepository.save(trabajador);
    }

    public void deleteTrabajador(Long id) {
        if (!trabajadorRepository.existsById(id)) {
            throw new RuntimeException("Trabajador no encontrado");
        }
        trabajadorRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return trabajadorRepository.existsById(id);
    }
    
     public List<Trabajador> findTrabajadoresByEmpresaId(Long empresaId) {
        return trabajadorRepository.findByEmpresaId(empresaId);
    }
    
}
