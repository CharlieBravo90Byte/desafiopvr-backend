/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * Entidad que representa una empresa.
 *
 * @author cabra
 * @version 27122024 
 */
@Entity
@Table(name = "tb_empresas")
public class Empresa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String rut;

    @Column(name = "razon_social",nullable = false)
    private String razonSocial;

    @Column(name = "fecha_insercion",nullable = false)
    private LocalDate fechaInsercion;

    @Column(nullable = false, unique = true)
    private String identificadorUnico;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Trabajador> trabajadores;
    
    // Getters y Setters

    /**
     * Obtiene el ID de la empresa
     * @return ID de la empresa
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la empresa
     * @param id nuevo ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el RUT de la empresa
     * @return RUT de la empresa
     */
    public String getRut() {
        return rut;
    }

    /**
     * Establece el RUT de la empresa
     * @param rut nuevo RUT
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * Obtiene la razón social de la empresa
     * @return razón social de la empresa
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * Establece la razón social de la empresa
     * @param razonSocial nueva razón social
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * Obtiene la fecha de inserción de la empresa
     * @return fecha de inserción
     */
    public LocalDate getFechaInsercion() {
        return fechaInsercion;
    }

    /**
     * Establece la fecha de inserción de la empresa
     * @param fechaInsercion nueva fecha de inserción
     */
    public void setFechaInsercion(LocalDate fechaInsercion) {
        this.fechaInsercion = fechaInsercion;
    }

    /**
     * Obtiene el identificador único de la empresa
     * @return identificador único
     */
    public String getIdentificadorUnico() {
        return identificadorUnico;
    }

    /**
     * Establece el identificador único de la empresa
     * @param identificadorUnico nuevo identificador único
     */
    public void setIdentificadorUnico(String identificadorUnico) {
        this.identificadorUnico = identificadorUnico;
    }

    /**
     * Obtiene la lista de trabajadores de la empresa
     * @return lista de trabajadores
     */
    public List<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    /**
     * Establece la lista de trabajadores de la empresa
     * @param trabajadores nueva lista de trabajadores
     */
    public void setTrabajadores(List<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    }
}
