package com.electiva.citas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electiva.citas.modelos.Medico;

@Repository
public interface MedicoRepository extends JpaRepository <Medico, Long> {
    
}
