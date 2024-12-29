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
    
    @Autowired
    private EmpresaRepository empresaRepository;

    @Transactional
    public Empresa crearEmpresa(Empresa empresa) {
        if (!RutValidator.validarRut(empresa.getRut())) {
            throw new IllegalArgumentException("RUT inválido");
        }
        empresa.setFechaInsercion(LocalDate.now());
        empresa.setIdentificadorUnico(IdentificadorUnicoGenerator.generarIdentificadorUnico());
        return empresaRepository.save(empresa);
    }

    public List<Empresa> obtenerTodasLasEmpresas() {
        return empresaRepository.findAll();
    }

    public Empresa obtenerEmpresaPorId(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
    }

    @Transactional
    public Empresa actualizarEmpresa(Long id, Empresa empresa) {
        Empresa empresaExistente = obtenerEmpresaPorId(id);
        if (!RutValidator.validarRut(empresa.getRut())) {
            throw new IllegalArgumentException("RUT inválido");
        }
        empresaExistente.setRazonSocial(empresa.getRazonSocial());
        empresaExistente.setRut(empresa.getRut());
        return empresaRepository.save(empresaExistente);
    }

    @Transactional
    public void eliminarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

    
}
