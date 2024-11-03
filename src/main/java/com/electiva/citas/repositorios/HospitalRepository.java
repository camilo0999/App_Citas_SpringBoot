package com.electiva.citas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electiva.citas.modelos.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    
}
