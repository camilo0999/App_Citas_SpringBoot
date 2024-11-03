package com.electiva.citas.servicios;

import java.util.List;

import com.electiva.citas.modelos.Historial;

public interface HistorialServices {

    public List<Historial> obtenerHistoriales();

    public void eliminarHistorial(Long id);


    
    
}
