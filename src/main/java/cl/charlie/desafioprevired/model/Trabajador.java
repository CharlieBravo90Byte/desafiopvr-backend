/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.charlie.desafioprevired.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad que representa un trabajador.
 *
 * @author cabra
 * @version 27122024
 */
@Entity
@Table(name = "tb_trabajadores")
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String rut;

    @Column(nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno",nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno",nullable = false)
    private String apellidoMaterno;

    @Column(nullable = false)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @JsonBackReference
    private Empresa empresa;
    
    // Getters y Setters
    
    /**
     * Obtiene el ID del trabajador
     * @return ID del trabajador
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del trabajador
     * @param id nuevo ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el RUT del trabajador
     * @return RUT del trabajador
     */
    public String getRut() {
        return rut;
    }

    /**
     * Establece el RUT del trabajador
     * @param rut nuevo RUT
     */
    public void setRut(String rut) {
        this.rut = rut;
    }

    /**
     * Obtiene el nombre del trabajador
     * @return nombre del trabajador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre
     * @param nombre nueva nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellidoPaterno
     * @return apellidoPaterno del trabajador
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Establece apellidoPaterno
     * @param apellidoPaterno nuevo apellidoPaterno
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Obtiene apellidoMaterno
     * @return apellidoMaterno del trabajador
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Establece el apellidoMaterno
     * @param apellidoMaterno nuevo apellidoMaterno
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Obtiene el direccion
     * @return direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece direccion
     * @param direccion nuevo direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    /**
     * Obtiene empresa
     * @return empresa
     */
    public Empresa getEmpresa() {
        return empresa;
    }

     /**
     * Establece empresa
     * @param empresa nueva empresa
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

   
    
    
}
