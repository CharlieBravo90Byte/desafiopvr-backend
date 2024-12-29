/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.desafioprevired.service;

import cl.charlie.desafioprevired.desafioprevired.model.Empresa;
import cl.charlie.desafioprevired.desafioprevired.model.Trabajador;
import cl.charlie.desafioprevired.desafioprevired.repository.EmpresaRepository;
import cl.charlie.desafioprevired.desafioprevired.repository.TrabajadorRepository;
import cl.charlie.desafioprevired.desafioprevired.utils.RutValidator;
import cl.charlie.desafioprevired.desafioprevired.utils.UniqueIdentifierGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cabra
 * @version 27122024
 */
@Service
public class EmpresaService {
    
    
     @Autowired
    private EmpresaRepository empresaRepository;
     
     @Autowired
    private TrabajadorRepository trabajadorRepository;
     
     @Autowired
    private EntityManager entityManager;
     

    public List<Empresa> getAllEmpresas() {
        return empresaRepository.findAll();
    }
    
    public Empresa getEmpresaById(Long id) {
        return empresaRepository.findById(id).orElse(null);
    }

    public Empresa createEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    } 
    
    
    public Empresa saveEmpresa(Empresa empresa) {
        if (!RutValidator.isValid(empresa.getRut())) {
            throw new IllegalArgumentException("Rut invalido");
        }
        empresa.setIdentificadorUnico(UniqueIdentifierGenerator.generate(20));
        return empresaRepository.save(empresa);
        
    }
    
     public Empresa updateEmpresa(Long id, Empresa empresa) {
        if (!empresaRepository.existsById(id)) {
            throw new RuntimeException("Empresa no encontrada");
        }
        empresa.setId(id);
        return empresaRepository.save(empresa);
    }

     
    public void deleteEmpresa(Long id) {
         if (!empresaRepository.existsById(id)) {
            throw new RuntimeException("Empresa no encontrada");
        }
        empresaRepository.deleteById(id);
    }
    
    
    public boolean existsById(Long id) {
        return empresaRepository.existsById(id);
    }
    
    
    public Trabajador addTrabajador(Long empresaId, Trabajador trabajador) {
        Optional<Empresa> empresaOpt = empresaRepository.findById(empresaId);
        if (empresaOpt.isPresent()) {
            Empresa empresa = empresaOpt.get();
            trabajador.setEmpresa(empresa);
            return trabajadorRepository.save(trabajador);
        } else {
            throw new RuntimeException("Empresa no encontrada");
        }
    }

    
}
