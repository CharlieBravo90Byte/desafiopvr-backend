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
    /**
     * Repositorio para operaciones con trabajadores
     */
    @Autowired
    private TrabajadorRepository trabajadorRepository;

    /**
     * Repositorio para operaciones con empresas
     */
    @Autowired
    private EmpresaRepository empresaRepository;

    /**
     * Crea un nuevo trabajador asociado a una empresa
     *
     * @param empresaId ID de la empresa a la que pertenecerá el trabajador
     * @param trabajador Datos del trabajador a crear
     * @return Trabajador creado con su ID asignado
     * @throws RuntimeException si la empresa no existe o el RUT ya está registrado
     */
    @Transactional
    public Trabajador saveTrabajador(Long empresaId, Trabajador trabajador) {
        // Verificar que la empresa existe
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException(
                    "Empresa no encontrada con ID: " + empresaId));

        // Validar RUT único
        if (trabajadorRepository.existsByRut(trabajador.getRut())) {
            throw new RuntimeException(
                "Ya existe un trabajador con el RUT: " + trabajador.getRut());
        }

        // Asociar trabajador con empresa y persistir
        trabajador.setEmpresa(empresa);
        return trabajadorRepository.save(trabajador);
    }
    
    /**
     * Busca un trabajador por su ID
     *
     * @param id ID del trabajador a buscar
     * @return Trabajador encontrado
     * @throws RuntimeException si el trabajador no existe
     */
    public Trabajador findById(Long id) {
        return trabajadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                    "Trabajador no encontrado con ID: " + id));
    }

    /**
     * Obtiene todos los trabajadores de una empresa
     *
     * @param empresaId ID de la empresa
     * @return Lista de trabajadores de la empresa
     */
    public List<Trabajador> findByEmpresaId(Long empresaId) {
        return trabajadorRepository.findByEmpresaId(empresaId);
    }
    
    /**
     * Actualiza los datos de un trabajador existente
     *
     * @param id ID del trabajador a actualizar
     * @param trabajadorDetails Nuevos datos del trabajador
     * @return Trabajador actualizado
     * @throws RuntimeException si el trabajador no existe o el nuevo RUT ya está registrado
     */
    @Transactional
    public Trabajador updateTrabajador(Long id, Trabajador trabajadorDetails) {
        // Obtener trabajador existente
        Trabajador trabajador = findById(id);
        
        // Validar RUT único si cambió
        if (!trabajador.getRut().equals(trabajadorDetails.getRut()) && 
            trabajadorRepository.existsByRut(trabajadorDetails.getRut())) {
            throw new RuntimeException(
                "Ya existe un trabajador con el RUT: " + trabajadorDetails.getRut());
        }

        // Actualizar campos permitidos
        trabajador.setRut(trabajadorDetails.getRut());
        trabajador.setNombre(trabajadorDetails.getNombre());
        trabajador.setApellidoPaterno(trabajadorDetails.getApellidoPaterno());
        trabajador.setApellidoMaterno(trabajadorDetails.getApellidoMaterno());
        trabajador.setDireccion(trabajadorDetails.getDireccion());

        // Persistir cambios
        return trabajadorRepository.save(trabajador);
    }

    /**
     * Elimina un trabajador del sistema
     *
     * @param id ID del trabajador a eliminar
     * @throws RuntimeException si el trabajador no existe
     */
    @Transactional
    public void deleteTrabajador(Long id) {
        // TODO: Descomentar para validar existencia antes de eliminar
        /*if (!trabajadorRepository.existsById(id)) {
            throw new RuntimeException("Trabajador no encontrado con ID: " + id);
        }*/
        trabajadorRepository.deleteById(id);
    }
    
}
