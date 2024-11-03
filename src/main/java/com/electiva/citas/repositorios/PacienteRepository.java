package com.electiva.citas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electiva.citas.modelos.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public Paciente findByCedula(String cedula);
}
