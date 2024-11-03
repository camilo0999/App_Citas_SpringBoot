package com.electiva.citas.modelos;


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String direccion;

    private String ciudad;

    private String telefono;

    private String nit;

    @OneToMany(mappedBy = "hospital")
    private List<Medico> medicos;

    @OneToMany(mappedBy = "hospital")
    private List<Citas> citas;



    public Hospital(String nombre, String direccion, String ciudad, String telefono, String nit) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.nit = nit;
    }

    public Hospital() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNit() {
        return this.nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public List<Medico> getMedicos() {
        return this.medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }

    public List<Citas> getCitas() {
        return this.citas;
    }

    public void setCitas(List<Citas> citas) {
        this.citas = citas;
    }



}
