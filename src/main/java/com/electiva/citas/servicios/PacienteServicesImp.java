package com.electiva.citas.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.electiva.citas.modelos.Paciente;
import com.electiva.citas.repositorios.PacienteRepository;

@Service
public class PacienteServicesImp implements PacienteServices {

    private final PacienteRepository pacienteRepository;

    public PacienteServicesImp(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void registrarPaciente(Paciente paciente) {
        try {
            pacienteRepository.save(paciente);
            System.out.println("Se ha registrado el paciente");
            
        } catch (Exception e) {
            System.out.println("Error al registrar el paciente");
        }
        

    }

    @Override
    public Paciente buscarPaciente(String cedula) {
       return pacienteRepository.findByCedula(cedula);
    }

    @Override
    public List<Paciente> obtenerPacientes() {
        return pacienteRepository.findAll();
    }



}
