/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.service;

import cl.charlie.desafioprevired.model.Trabajador;
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

    @Transactional
    public Trabajador crearTrabajador(Trabajador trabajador) {
        if (!RutValidator.validarRut(trabajador.getRut())) {
            throw new IllegalArgumentException("RUT inválido");
        }
        return trabajadorRepository.save(trabajador);
    }

    public List<Trabajador> obtenerTodosLosTrabajadores() {
        return trabajadorRepository.findAll();
    }

    public Trabajador obtenerTrabajadorPorId(Long id) {
        return trabajadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabajador no encontrado"));
    }

    @Transactional
    public Trabajador actualizarTrabajador(Long id, Trabajador trabajador) {
        Trabajador trabajadorExistente = obtenerTrabajadorPorId(id);
        if (!RutValidator.validarRut(trabajador.getRut())) {
            throw new IllegalArgumentException("RUT inválido");
        }
        trabajadorExistente.setNombre(trabajador.getNombre());
        trabajadorExistente.setApellidoPaterno(trabajador.getApellidoPaterno());
        trabajadorExistente.setApellidoMaterno(trabajador.getApellidoMaterno());
        trabajadorExistente.setDireccion(trabajador.getDireccion());
        trabajadorExistente.setRut(trabajador.getRut());
        return trabajadorRepository.save(trabajadorExistente);
    }

    @Transactional
    public void eliminarTrabajador(Long id) {
        trabajadorRepository.deleteById(id);
    }
    
}
