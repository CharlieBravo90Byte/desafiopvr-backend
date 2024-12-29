/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.service;

import cl.charlie.desafioprevired.model.Empresa;
import cl.charlie.desafioprevired.model.Trabajador;
import cl.charlie.desafioprevired.repository.EmpresaRepository;
import cl.charlie.desafioprevired.repository.TrabajadorRepository;
import cl.charlie.desafioprevired.utils.IdentificadorUnicoGenerator;
import cl.charlie.desafioprevired.utils.RutValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Clase de servicios de Empresa
 *
 * @author cabra
 * @version 27122024
 */
@Service
public class EmpresaService {
    
    /**
     * Repositorio para operaciones con la base de datos
     */
    @Autowired
    private EmpresaRepository empresaRepository;

    /**
     * Crea una nueva empresa en el sistema
     * 
     * @param empresa Datos de la empresa a crear
     * @return Empresa creada con su ID asignado
     * @throws IllegalArgumentException si los datos de la empresa son inválidos
     */
    @Transactional
    public Empresa crearEmpresa(Empresa empresa) {
        // Establecer datos automáticos
        empresa.setFechaInsercion(LocalDate.now());
        empresa.setIdentificadorUnico(
            IdentificadorUnicoGenerator.generarIdentificadorUnico()
        );
        
        // Persistir y retornar la nueva empresa
        return empresaRepository.save(empresa);
    }

    /**
     * Obtiene todas las empresas registradas
     * 
     * @return Lista de todas las empresas
     */
    public List<Empresa> obtenerTodasLasEmpresas() {
        return empresaRepository.findAll();
    }

    /**
     * Busca una empresa por su ID
     * 
     * @param id ID de la empresa a buscar
     * @return Empresa encontrada
     * @throws RuntimeException si la empresa no existe
     */
    public Empresa obtenerEmpresaPorId(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("Empresa no encontrada con ID: " + id)
                );
    }

    /**
     * Actualiza los datos de una empresa existente
     * 
     * @param id ID de la empresa a actualizar
     * @param empresa Nuevos datos de la empresa
     * @return Empresa actualizada
     * @throws RuntimeException si la empresa no existe
     * @throws IllegalArgumentException si los datos son inválidos
     */
    @Transactional
    public Empresa actualizarEmpresa(Long id, Empresa empresa) {
        // Verificar que la empresa existe
        Empresa empresaExistente = obtenerEmpresaPorId(id);

        // TODO: Descomentar cuando se implemente la validación de RUT
        /*if (!RutValidator.validarRut(empresa.getRut())) {
            throw new IllegalArgumentException("RUT inválido");
        }*/

        // Actualizar solo los campos permitidos
        empresaExistente.setRazonSocial(empresa.getRazonSocial());
        empresaExistente.setRut(empresa.getRut());

        // Persistir y retornar la empresa actualizada
        return empresaRepository.save(empresaExistente);
    }

    /**
     * Elimina una empresa del sistema
     * 
     * @param id ID de la empresa a eliminar
     * @throws RuntimeException si la empresa no existe
     */
    @Transactional
    public void eliminarEmpresa(Long id) {
        // Verificar que la empresa existe antes de eliminar
        if (!empresaRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: empresa no encontrada con ID: " + id);
        }
        empresaRepository.deleteById(id);
    }

    
}
