package com.electiva.citas.servicios;

import java.util.List;

import com.electiva.citas.modelos.Citas;
import com.electiva.citas.modelos.CitasJson;

public interface CitasServices {

    public void registrarCitas(Citas citas, String cedula, Long idMedico);

    public List<Citas> obtenerCitas();

    public void cancelarCitas(Long idCita);

    public void confirmarCitas(Long idCita);

    public List<CitasJson> citasJson();

    public int analisis(String estado);

    public Long contar();



    
}
