package com.electiva.citas.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.electiva.citas.modelos.Hospital;
import com.electiva.citas.repositorios.HospitalRepository;

@Service
public class HospitalServicesImp implements HospitalServices {

    private final HospitalRepository hospitalRepository;

    public HospitalServicesImp(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public void registrarHospital(Hospital hospital) {

        try {
            hospitalRepository.save(hospital);
            System.out.println("Se ha registrado el hospital");

        } catch (Exception e) {
            System.out.println("Error al registrar el hospital");
        }

    }

    @Override
    public List<Hospital> obtenerHospitales() {
        return hospitalRepository.findAll();
    }

    @Override
    public Hospital obtenerHospitalPorId(Long id) {

        return hospitalRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarHospital(Long id) {

        hospitalRepository.deleteById(id);
    }
    
}
