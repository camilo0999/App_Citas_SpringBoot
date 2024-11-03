package com.electiva.citas.servicios;

import java.util.List;

import org.springframework.stereotype.Service;

import com.electiva.citas.modelos.Historial;
import com.electiva.citas.repositorios.HistorialRepository;

@Service
public class HistorialServicesIm implements HistorialServices{

    private final HistorialRepository historialRepository;

    public HistorialServicesIm(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    @Override
    public List<Historial> obtenerHistoriales() {
        return historialRepository.findAll();
    }

    @Override
    public void eliminarHistorial(Long id) {
        
        historialRepository.deleteById(id);

    }
    
}
