/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package cl.charlie.desafioprevired.repository;

import cl.charlie.desafioprevired.model.Empresa;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Empresa
 *
 * @author cabra
 * @version 27122024
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
    boolean existsByRut(String rut);
    boolean existsByIdentificadorUnico(String identificadorUnico);
    
}
