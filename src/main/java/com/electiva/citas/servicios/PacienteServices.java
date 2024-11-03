package com.electiva.citas.servicios;

import java.util.List;

import com.electiva.citas.modelos.Paciente;

public interface PacienteServices {

    public void registrarPaciente(Paciente paciente);

    public Paciente buscarPaciente(String cedula);

    public List<Paciente> obtenerPacientes();
    
    
}
