package com.electiva.citas.servicios;

import java.util.List;

import com.electiva.citas.modelos.Hospital;

public interface HospitalServices {

    public void registrarHospital(Hospital hospital);

    public List<Hospital> obtenerHospitales();

    public Hospital obtenerHospitalPorId(Long id);

    public void eliminarHospital(Long id);

    

    
    
}
