package com.electiva.citas.servicios;

import java.util.List;

import com.electiva.citas.modelos.Medico;

public interface Medicoservices {

    public void registrarMedico(Medico medico, Long idHospital);

    public List<Medico> obtenerMedicos();

    public Medico buscarMedico(Long id);

}