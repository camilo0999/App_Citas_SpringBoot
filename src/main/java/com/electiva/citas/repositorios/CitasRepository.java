package com.electiva.citas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electiva.citas.modelos.Citas;

@Repository
public interface CitasRepository extends JpaRepository<Citas, Long> {

    int countByEstadoContaining(String estado);

    long count();
    


    
}
